package fafi.hystrix.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.strategy.properties.HystrixProperty;
import fafi.Impl.OrderService;


public class QueryOrderIdCommandThread implements Runnable {

    private OrderService orderService;

    public QueryOrderIdCommandThread(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        HystrixCommand<Integer> command = new QueryOrderIdCommand(orderService);
        Integer r = command.execute();
        String method = r == -1 ? "fallback" : "run";
        HystrixProperty<Integer> timeout = command.getProperties().executionTimeoutInMilliseconds();
        System.out.println("result:"+ Thread.currentThread().getId()+"---"+method+"----"+r + ":" + command.isCircuitBreakerOpen()+":"+timeout.get());
    }
}
