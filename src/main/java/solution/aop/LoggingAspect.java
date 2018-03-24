package solution.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import solution.utils.logging.EventLogger;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private final EventLogger logger;

    @Autowired
    public LoggingAspect(@Qualifier("fileEventLogger") EventLogger logger) {
        this.logger = logger;
    }

    @Pointcut("execution(String solution.controllers.*.*(..))")
    private void allControllers() {
    }

    @Before("allControllers()")
    private void consoleBefore(JoinPoint joinPoint) {
        String msg = "BEFORE: " + joinPoint.getTarget().getClass().getCanonicalName() +
                " " + joinPoint.getSignature().getName();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RequestMapping annotation = method.getAnnotation(RequestMapping.class);
        if (annotation != null){
            msg += (Arrays.toString(annotation.path()) + " VIA " + Arrays.toString(annotation.method()));
        }
        logger.logMessage(msg);
    }

    @AfterReturning(pointcut = "allControllers()", returning = "retVal")
    private void consoleAfter(String retVal) {
        logger.logMessage(retVal);
    }


}
