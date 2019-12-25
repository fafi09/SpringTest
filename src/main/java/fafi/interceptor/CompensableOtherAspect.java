package fafi.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

public abstract class CompensableOtherAspect {
    private CompensableOtherInterceptor compensableInterceptor;

    public void setCompensableTransactionInterceptor(CompensableOtherInterceptor compensableInterceptor) {
        this.compensableInterceptor = compensableInterceptor;
    }

    @Pointcut("@annotation(fafi.annotation.Compensable)")
    public void compensableOtherService() {
        System.out.println("compensableOtherService");
    }

    @Around("compensableOtherService()")
    public Object interceptCompensableOtherMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("interceptCompensableOtherMethod....");
        return compensableInterceptor.interceptCompensableOtherMethod(pjp);
    }

    public abstract int getOrder();
}
