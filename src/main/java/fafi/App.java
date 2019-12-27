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

    /**
     * https://github.com/fafi09/SpringTest.git
     * @param args
     */
    public static void main( String[] args ) {
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringCompensable.xml");
        CaptialService service = (CaptialService) context.getBean(CaptialService.class);
        service.doRecord();

        SubstractService substractService = (SubstractService) context.getBean("substractService");
        int result = substractService.substract(5,2);
        System.out.println(result);
        CheckArithtectArgService checkService = (CheckArithtectArgService) substractService;
        boolean ret = checkService.checkArithArg(5);
        if(ret) {
            System.out.println("check true-------------");
        } else {
            System.out.println("check false-------------");
        }
    }
}
