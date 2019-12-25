package fafi.interceptor;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;

@Aspect
public class ConfigurableOtherAspect extends CompensableOtherAspect implements Ordered {
    public void init() {


        CompensableOtherInterceptor compensableInterceptor = new CompensableOtherInterceptor();

        this.setCompensableTransactionInterceptor(compensableInterceptor);
    }
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE+1;
    }
}
