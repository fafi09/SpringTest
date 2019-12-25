package fafi.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;

public class CompensableOtherInterceptor {
    public Object interceptCompensableOtherMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("interceptCompensableOtherMethod");
        return pjp.proceed();
    }
}
