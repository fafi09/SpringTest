package fafi.interceptor;

import fafi.Impl.CheckArithtectArgService;
import fafi.Impl.CheckArithtectArgServiceImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class IntroductionAspect {
    @DeclareParents(value = "fafi.Impl.SubstractServiceImpl", defaultImpl = CheckArithtectArgServiceImpl.class)
    public CheckArithtectArgService userValidator;

    @Pointcut("execution(* fafi.Impl.SubstractServiceImpl.substract(..))")
    public void pointCut() {
    }

    @Before("pointCut()&&args(a,b)")
    public void before(JoinPoint point , Integer a, Integer b) {
        Object[] args=point.getArgs();
        Integer arg0= (Integer)args[0];
        System.out.println("print in before"+a+":"+b);
        System.out.println("\nprint in before args = "+arg0);
        System.out.println("before-----------");
    }

    @Around("pointCut()")
    public Integer around(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("around before-------------");
//        boolean ret = userValidator.checkArithArg((Integer) jp.getArgs()[0]);
//        if(ret) {
//            System.out.println("check true-------------");
//        } else {
//            System.out.println("check false-------------");
//        }
        Integer o = (Integer) jp.proceed(jp.getArgs());
        System.out.println("around after-------------");
        return o;
    }

    @After("pointCut()")
    public void after() {
        System.out.println("after------------");
    }

    @AfterReturning("pointCut()")
    public void afterReturning() {
        System.out.println("afterReturning------------");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowning() {
        System.out.println("afterThrowning------------");
    }
}
