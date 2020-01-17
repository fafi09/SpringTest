package fafi;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class RocketMqConsumer {
    public static void main(String[] args) {
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
            pushConsumer.subscribe("TopicTestSyncProducer", "TagA || TagC || TagD");
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
