package fafi.interceptor;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
@Aspect
public class ConfigurableAspect extends CompensableAspect implements Ordered {
    public void init() {


        CompensableInterceptor compensableInterceptor = new CompensableInterceptor();

        this.setCompensableTransactionInterceptor(compensableInterceptor);
    }
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
