package solution.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import solution.utils.logging.EventLogger;

import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class StatisticAspect {

    private Map<Class<?>, Integer> statistic = new HashMap<Class<?>, Integer>();

    @Pointcut("execution(* *.logEvent(..))")
    private void logEventMethodCall(){}

    @AfterReturning(pointcut = "logEventMethodCall()")
    private void updateStatistic(JoinPoint joinPoint){
        Class<?> clazz = joinPoint.getTarget().getClass();
        Integer integer = statistic.get(clazz);
        if (integer == null) {
            statistic.put(clazz, 1);
        } else {
            statistic.put(clazz, ++integer);
        }
    }

    public Map<Class<?>, Integer> getStatistic() {
        return statistic;
    }
}
