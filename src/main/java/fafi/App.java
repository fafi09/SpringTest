package fafi;
import com.netflix.client.ClientException;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.config.ConfigurationManager;
import com.netflix.loadbalancer.ZoneAwareLoadBalancer;
import com.netflix.niws.client.http.RestClient;
import com.netflix.client.ClientFactory;
import fafi.Impl.*;
import fafi.hystrix.command.QueryOrderIdCommand;
import fafi.hystrix.command.QueryOrderIdCommandThread;
import fafi.pojo.User;
import fafi.processor.MyBeanPostProcessor;
import fafi.processor.MyBeanPostProcessor2;
import fafi.redis.service.RedisService;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.cglib.beans.BeanGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import javax.activation.MailcapCommandMap;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.*;

/**
 * Hello world!
 *
 */
public class App 
{
//    public static void main( String[] args )
//    {
//        ApplicationContext context = new ClassPathXmlApplicationContext("SpringConfig.xml");
//        //OrderServiceImpl service = new OrderServiceImpl();
//        //OrderService service = (OrderService) context.getBean(OrderService.class);
//        //int b = service.add(4,5);
//        OrderService proxy = (OrderService) context.getBean("proxy");
//        int c = proxy.add(4,5);
//        System.out.println( c);
//        proxy.doPrint();
//    }

    /**
     * https://github.com/fafi09/SpringTest.git
     * @param args
     */
//    public static void main( String[] args ) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("SpringCompensable.xml");
//        CaptialService service = (CaptialService) context.getBean(CaptialService.class);
//        service.doRecord();
//
//        SubstractService substractService = (SubstractService) context.getBean("substractService");
//        int result = substractService.substract(5,2);
//        System.out.println(result);
//        CheckArithtectArgService checkService = (CheckArithtectArgService) substractService;
//        boolean ret = checkService.checkArithArg(5);
//        if(ret) {
//            System.out.println("check true-------------");
//        } else {
//            System.out.println("check false-------------");
//        }
//
//        User user = context.getBean(User.class);
//        System.out.println(user);
//    }

    /**
     * BeanFactory和ApplicationContext两个容器对待bean的后置处理器稍微有些不同。ApplicationContext容器会自动检测Spring配置文件中那些bean所对应的Java类实现了BeanPostProcessor接口，并自动把它们注册为后置处理器。在创建bean过程中调用它们，所以部署一个后置处理器跟普通的bean没有什么太大区别。
     * BeanFactory容器注册bean后置处理器时必须通过代码显示的注册，在IoC容器继承体系中的ConfigurableBeanFactory接口中定义了注册方法
     * @param args
     */
//    public static void main(String[] args) {
//        XmlBeanFactory bf = new XmlBeanFactory(new ClassPathResource("SpringCompensable.xml"));
//        // 显示添加后置处理器
//        bf.addBeanPostProcessor(bf.getBean(MyBeanPostProcessor.class));
//        bf.addBeanPostProcessor(bf.getBean(MyBeanPostProcessor2.class));
//        User user = bf.getBean(User.class);
//        System.out.println(user);
//    }
//    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-redis.xml");
//        RedisService service = (RedisService) context.getBean(RedisService.class);
//        service.set("pass","fsd");
//    }
//    public static void main(String[] args) {
//        OrderService orderService = new OrderServiceImpl();
//        //Executors.newFixedThreadPool()
//        //ExecutorService service = new ThreadPoolExecutor(10, 50, 1000, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>() );
//
//        QueryOrderIdCommandThread thread = new QueryOrderIdCommandThread(orderService);
//
//        for(int i = 0; i < 50; i++) {
//            //service.execute(thread);
//            Thread t = new Thread(thread);
//            t.start();
//        }
//
//    }

    /**
     * ribbon test
     * @param args
     */
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException, ClientException {
        ConfigurationManager.loadPropertiesFromResources("sample-client.properties");
        System.out.println(ConfigurationManager.getConfigInstance().getProperty("sample-client.ribbon.listOfServers"));

        RestClient client = (RestClient)ClientFactory.getNamedClient("sample-client");
        HttpRequest request = HttpRequest.newBuilder().uri(new URI("/")).build();

        for(int i = 0; i < 20; i ++) {
            HttpResponse response = client.executeWithLoadBalancer(request);
            System.out.println("Status for URI:" + response.getRequestedURI() + " is :" + response.getStatus());
        }

        ZoneAwareLoadBalancer lb = (ZoneAwareLoadBalancer) client.getLoadBalancer();
        System.out.println(lb.getLoadBalancerStats());

        ConfigurationManager.getConfigInstance().setProperty("sample-client.ribbon.listOfServers", "www.baidu.com:80,www.linkedin.com:80");

        //ConfigurationManager.getConfigInstance().
        //client = (RestClient)ClientFactory.getNamedClient("sample-client");
        System.out.println("changing servers ...");
        System.out.println(ConfigurationManager.getConfigInstance().getProperty("sample-client.ribbon.listOfServers"));
        Thread.sleep(3000);

        for(int i = 0; i < 20; i ++) {
            HttpResponse response = client.executeWithLoadBalancer(request);
            System.out.println("Status for URI:" + response.getRequestedURI() + " is :" + response.getStatus());
        }
        System.out.println(lb.getLoadBalancerStats());
    }
}
