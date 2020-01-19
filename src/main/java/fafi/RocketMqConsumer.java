package fafi;

import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.PullResult;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RocketMqConsumer {

    public static void main(String[] args) throws MQClientException {
        //pushConsumer();
        pullConsumer();
    }
    private static final Map<MessageQueue, Long> offsetTable = new HashMap<MessageQueue, Long>();

    private static void putMessageQueueOffset(MessageQueue mq, long offset) {
        offsetTable.put(mq, offset);
    }

    private static long getMessageQueueOffset(MessageQueue mq) {
        Long offset = offsetTable.get(mq);
        if (offset != null) {
            return offset;
        }
        return 0;
    }

    public static void pullConsumer() throws MQClientException {
        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer("pullConsumer");
        consumer.setNamesrvAddr("192.168.233.135:9876");
        consumer.setInstanceName("consumer");
        consumer.start();

        Set<MessageQueue> mqs = consumer.fetchSubscribeMessageQueues("TopicTestAsyncProducer");
        for (MessageQueue mq : mqs) {
            System.out.printf("Consume from the queue: %s%n", mq);
            //System.out.println("Consume from the queue: " + mq);
            //	long offset = consumer.fetchConsumeOffset(mq, true);
            //	PullResultExt pullResult =(PullResultExt)consumer.pull(mq, null, getMessageQueueOffset(mq), 32);
            //消息未到达默认是阻塞10秒，private long consumerPullTimeoutMillis = 1000 * 10;
            SINGLE_MQ:
            while (true) {
                try {
                    PullResult pullResult =
                            consumer.pullBlockIfNotFound(mq, null, getMessageQueueOffset(mq), 32);
                    System.out.printf("%s%n", pullResult);
                    putMessageQueueOffset(mq, pullResult.getNextBeginOffset());
                    switch (pullResult.getPullStatus()) {
                        case FOUND:
                            System.out.println(pullResult.getMsgFoundList().get(0).toString());
                            break;
                        case NO_NEW_MSG:
                            break SINGLE_MQ;
                        case NO_MATCHED_MSG:
                        case OFFSET_ILLEGAL:
                            break;
                        default:
                            break;
                    }
                } catch (Exception e) {
                    //TODO
                }
            }
        }
    }

    public static void pushConsumer() {
        /**
         * 注意：ConsumerGroupName需要由应用来保证唯一
         */
        DefaultMQPushConsumer pushConsumer = new DefaultMQPushConsumer("please_rename_unique_group_name");

        pushConsumer.setNamesrvAddr("192.168.233.135:9876");
        pushConsumer.setInstanceName("Consumer");
        try {
            /**
             * 订阅指定topic下tags分别等于TagA或TagC或TagD
             * 两个参数：第一个参数是topic第二个参数是tags
             */
            //TopicTestSyncProducer
            //pushConsumer.subscribe("TopicTest", "TagA || TagC || TagD");
            pushConsumer.subscribe("TopicTestAsyncProducer", "TagA || TagC || TagD");
            /**
             * 订阅指定topic下所有消息<br>
             * 注意：一个consumer对象可以订阅多个topic
             */
            //pushConsumer.subscribe("TopicTest2", "*");
            pushConsumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                                ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                    System.out.println(Thread.currentThread().getName() + " Receive New Messages: " + msgs.size());
                    MessageExt messageExt = msgs.get(0);
                    if("TopicTest".equals(messageExt.getTopic())){
                        // 执行TopicTest1的消费逻辑
                        if (messageExt.getTags() != null && messageExt.getTags().equals("TagA")) {
                            // 执行TagA的消费
                            System.out.println(new String(messageExt.getBody()));
                        }else if(messageExt.getTags() != null && messageExt.getTags().equals("TagB")){
                            System.out.println(new String(messageExt.getBody()));
                        }else if(messageExt.getTags() != null && messageExt.getTags().equals("TagC")) {
                            System.out.println(new String(messageExt.getBody()));
                        }
                    }else if("TopicTest2".equals(messageExt.getTopic())){
                        System.out.println(new String(messageExt.getBody()));
                    }else if("TopicTestSyncProducer".equals(messageExt.getTopic())) {
                        System.out.println(new String(messageExt.getBody()));
                    }else if("TopicTestAsyncProducer".equals(messageExt.getTopic())) {
                        System.out.println(new String(messageExt.getBody()));
                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        /**
         * Consumer对象在使用之前必须要调用start初始化，初始化一次即可<br>
         */
        try {
            pushConsumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        System.out.println("Consumer Started.");
    }
}
