package fafi.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;

public class CompensableInterceptor {
    public Object interceptCompensableMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("interceptCompensableMethod");
        return pjp.proceed();
    }
}
