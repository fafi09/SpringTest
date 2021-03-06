package fafi;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import rx.Producer;

import java.io.UnsupportedEncodingException;

public class RocketMqProductor {

    /**
     * 可靠同步发送在众多场景中被使用，例如重要的通知消息、短信通知、短信营销系统，等等
     * @param args
     * @throws MQClientException
     * @throws UnsupportedEncodingException
     * @throws RemotingException
     * @throws InterruptedException
     * @throws MQBrokerException
     */
//    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
//        //Instantiate with a producer group name.
//        DefaultMQProducer producer = new
//                DefaultMQProducer("please_rename_unique_group_name");
//        producer.setNamesrvAddr("192.168.233.135:9876");
//        //Launch the instance.
//        producer.start();
//        for (int i = 0; i < 10; i++) {
//            //Create a message instance, specifying topic, tag and message body.
//            Message msg = new Message("TopicTest" /* Topic */,
//                    "TagA" /* Tag */,
//                    ("Hello RocketMQ " +
//                            i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
//            );
//            //Call send message to deliver message to one of brokers.
//            SendResult sendResult = producer.send(msg);
//            System.out.printf("%s%n", sendResult);
//        }
//        //Shut down once the producer instance is not longer in use.
//        producer.shutdown();
//    }



    public static void main(String[] args) throws InterruptedException, RemotingException, MQClientException, UnsupportedEncodingException {
        asyncProducer();
    }

    public static void syncProducer() throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new
                DefaultMQProducer("SyncProducer");
        //Launch the instance.
        producer.setNamesrvAddr("192.168.233.135:9876");
        producer.start();
        for (int i = 0; i < 10; i++) {
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("TopicTestSyncProducer" /* Topic */,
                    "TagA" /* Tag */,
                    ("Hello RocketMQ " +
                            i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            //Call send message to deliver message to one of brokers.
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }

    public static void asyncProducer() throws MQClientException, RemotingException, InterruptedException, UnsupportedEncodingException {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new DefaultMQProducer("AsyncProducer");
        //Launch the instance.
        producer.setNamesrvAddr("192.168.233.135:9876");
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("TopicTestAsyncProducer",
                    "TagA",
                    "OrderID188",
                    "Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));
            producer.send(msg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.printf("%-10d OK %s %n", index,
                            sendResult.getMsgId());
                }
                @Override
                public void onException(Throwable e) {
                    System.out.printf("%-10d Exception %s %n", index, e);
                    e.printStackTrace();
                }
            });
        }
        //由于是异步的，延迟关闭producer，防止提前关闭
        Thread.sleep(2000);
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }

}
