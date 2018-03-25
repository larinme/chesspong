package solution.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import solution.utils.logging.EventLogger;

import javax.servlet.http.HttpServletRequest;
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

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        logger.logMessage(msg + " " + request.getRequestURL());
    }

    @AfterReturning(pointcut = "allControllers()", returning = "retVal")
    private void consoleAfter(String retVal) {
        logger.logMessage(retVal);
    }


}
