package fafi.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

public abstract class CompensableAspect {
    private CompensableInterceptor compensableInterceptor;

    public void setCompensableTransactionInterceptor(CompensableInterceptor compensableInterceptor) {
        this.compensableInterceptor = compensableInterceptor;
    }

    @Pointcut("@annotation(fafi.annotation.Compensable)")
    public void compensableService() {
        System.out.println("compensableService");
    }

    @Around("compensableService()")
    public Object interceptCompensableMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("interceptCompensableMethod....");
        return compensableInterceptor.interceptCompensableMethod(pjp);
    }

    public abstract int getOrder();
}
