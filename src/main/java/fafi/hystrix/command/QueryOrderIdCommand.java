package fafi.hystrix.command;

import com.netflix.hystrix.*;
import fafi.Impl.OrderService;

public class QueryOrderIdCommand extends HystrixCommand<Integer> {
    private OrderService orderService;
    public QueryOrderIdCommand(OrderService orderService) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("orderService"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("queryByOrderId"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withCircuitBreakerRequestVolumeThreshold(10)//至少有10个请求，熔断器才进行错误率的计算
                        .withCircuitBreakerSleepWindowInMilliseconds(5000)//熔断器中断请求5秒后会进入半打开状态,放部分流量过去重试
                        .withCircuitBreakerErrorThresholdPercentage(50)//错误率达到50开启熔断保护
                        .withExecutionTimeoutEnabled(true)
                        .withRequestLogEnabled(true)
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
                        //HystrixCommand.GetFallback()的最大数量，默认10。超出时将会有异常抛出，注意：该项配置对于THREAD隔离模式也起作用
                        .withFallbackIsolationSemaphoreMaxConcurrentRequests(60)
                        .withExecutionTimeoutInMilliseconds(1000)
                        //java.lang.InterruptedException: sleep interrupted 解决
                        .withExecutionIsolationThreadInterruptOnTimeout(false)
                //.withExecutionIsolationSemaphoreMaxConcurrentRequests(60)
                )
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties
                        .Setter().withCoreSize(10)));
        this.orderService = orderService;

    }

    @Override
    protected Integer run() throws Exception {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return orderService.add(3,4);
    }

    @Override
    protected Integer getFallback() {
        return -1;
    }
}
