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
