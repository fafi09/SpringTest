package fafi;
import fafi.Impl.*;
import fafi.pojo.User;
import fafi.processor.MyBeanPostProcessor;
import fafi.processor.MyBeanPostProcessor2;
import fafi.redis.service.RedisService;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.cglib.beans.BeanGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

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
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-redis.xml");
        RedisService service = (RedisService) context.getBean(RedisService.class);
        service.set("pass","fsd");
    }
}
