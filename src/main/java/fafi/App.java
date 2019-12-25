package fafi;
import fafi.Impl.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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

    public static void main( String[] args ) {
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringCompensable.xml");
        CaptialService service = (CaptialService) context.getBean(CaptialService.class);
        service.doRecord();
    }
}
