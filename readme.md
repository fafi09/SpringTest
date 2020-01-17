SpringCompensable.xml 为aspectj
SpringConfig.xml 为spring-aop


redis
ps -ef | grep redis
./redis-cli -h 127.0.0.1 -p 6379 shutdown
./redis-server ../redis.conf

ribbon
出现discoverable via the ServiceLoader错误
加入下列依赖
<dependency>
      <groupId>com.netflix.ribbon</groupId>
      <artifactId>ribbon</artifactId>
      <version>2.7.17</version>
    </dependency>

================
启动RocketMq
启动Name Server
nohup sh bin/mqnamesrv &
tail -f ~/logs/rocketmqlogs/namesrv.log

启动Broker
nohup sh bin/mqbroker -n localhost:9876 &
tail -f ~/logs/rocketmqlogs/broker.log

关闭所有服务器
sh bin/mqshutdown broker
sh bin/mqshutdown namesrv

发送和接收消息
export NAMESRV_ADDR=localhost:9876
sh bin/tools.sh org.apache.rocketmq.example.quickstart.Producer
 SendResult [sendStatus=SEND_OK, msgId= ...
 
sh bin/tools.sh org.apache.rocketmq.example.quickstart.Consumer
 ConsumeMessageThread_%d Receive New Messages: [MessageExt...
 
 #查看消费连接
 sh mqadmin  consumerConnection -n 192.168.233.135:9876 -g please_rename_unique_group_name
 
 #查询消费进程信息
 sh mqadmin  consumerProgress -n 192.168.233.135:9876
 
 #查看主题
 sh mqadmin  topicList -n 192.168.233.135:9876
 
 #显示主题，消费者的tps状态
 sh mqadmin  statsAll -n 192.168.233.135:9876
 
 #打印主题相关的msg
 sh mqadmin printMsg -t TopicTest
 sh mqadmin printMsg -t TopicTest -n 192.168.233.135:9876
  
 [root@bogon bin]# sh mqadmin
 The most commonly used mqadmin commands are:
    updateTopic          Update or create topic
    deleteTopic          Delete topic from broker and NameServer.
    updateSubGroup       Update or create subscription group
    deleteSubGroup       Delete subscription group from broker.
    updateBrokerConfig   Update broker's config
    updateTopicPerm      Update topic perm
    topicRoute           Examine topic route info
    topicStatus          Examine topic Status info
    topicClusterList     get cluster info for topic
    brokerStatus         Fetch broker runtime status data
    queryMsgById         Query Message by Id
    queryMsgByKey        Query Message by Key
    queryMsgByUniqueKey  Query Message by Unique key
    queryMsgByOffset     Query Message by offset
    printMsg             Print Message Detail
    printMsgByQueue      Print Message Detail
    sendMsgStatus        send msg to broker.
    brokerConsumeStats   Fetch broker consume stats data
    producerConnection   Query producer's socket connection and client version
    consumerConnection   Query consumer's socket connection, client version and subscription
    consumerProgress     Query consumers's progress, speed
    consumerStatus       Query consumer's internal data structure
    cloneGroupOffset     clone offset from other group.
    clusterList          List all of clusters
    topicList            Fetch all topic list from name server
    updateKvConfig       Create or update KV config.
    deleteKvConfig       Delete KV config.
    wipeWritePerm        Wipe write perm of broker in all name server
    resetOffsetByTime    Reset consumer offset by timestamp(without client restart).
    updateOrderConf      Create or update or delete order conf
    cleanExpiredCQ       Clean expired ConsumeQueue on broker.
    cleanUnusedTopic     Clean unused topic on broker.
    startMonitoring      Start Monitoring
    statsAll             Topic and Consumer tps stats
    allocateMQ           Allocate MQ
    checkMsgSendRT       check message send response time
    clusterRT            List All clusters Message Send RT
    getNamesrvConfig     Get configs of name server.
    updateNamesrvConfig  Update configs of name server.
    getBrokerConfig      Get broker config by cluster or special broker!
    queryCq              Query cq command.
    sendMessage          Send a message
    consumeMessage       Consume message
    updateAclConfig      Update acl config yaml file in broker
    deleteAccessConfig   Delete Acl Config Account in broker
    clusterAclConfigVersion List all of acl config version information in cluster
    updateGlobalWhiteAddr Update global white address for acl Config File in broker
    getAccessConfigSubCommand List all of acl config information in cluster
 
 See 'mqadmin help <command>' for more information on a specific command.
 
#Query consumer's internal data structure
sh mqadmin consumerStatus -g please_rename_unique_group_name -n 192.168.233.135:9876

#Examine topic route info 
sh mqadmin topicRoute -n 192.168.233.135:9876 -t TopicTestSyncProducer

#消费msg
sh mqadmin consumeMessage -t TopicTestSyncProducer -n 192.168.233.135:9876

#查询msg -i 后为offsetMsgId
sh mqadmin queryMsgById -n 192.168.233.135:9876 -i C0A8E98700002A9F0000000000039F84

#取得主题状态
sh mqadmin topicStatus -n 192.168.233.135:9876 -t TopicTestSyncProducer
==================