package solution;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import solution.aop.StatisticAspect;
import solution.converters.Converter;
import solution.model.Match;
import solution.model.enums.MatchResult;

import java.util.Map;

public class Main {



    public static void main(String[] args) {

        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        Match match = context.getBean(Match.class);
        Converter converter = context.getBean(Converter.class);
        MatchResult matchResult = converter.defineResult(match);
        System.out.println("STATISTIC TIME");
        StatisticAspect aspect = context.getBean(StatisticAspect.class);
        context.close();
        Map<Class<?>, Integer> statistic = aspect.getStatistic();
        for (Map.Entry<Class<?>, Integer> entry : statistic.entrySet()) {
            System.out.println(entry.getKey() + " called " + entry.getValue() + " times.");
        }

    }
}
