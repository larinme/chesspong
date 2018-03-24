package solution.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* *.logEvent(..))")
    private void allLogEventMethods(){}

    @Pointcut("execution(* *.defineResult(..))")
    private void allDefineResultMethods(){}

    @Before("allLogEventMethods()")
    private void logBefore(JoinPoint joinPoint){
        System.out.println("BEFORE: " + joinPoint.getTarget().getClass().getCanonicalName() +
        " " + joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "allDefineResultMethods()", returning = "retVal")
    private void afterLog(Object retVal){
        System.out.println("Return val: " + retVal);
    }
}
