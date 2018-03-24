package solution.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import solution.model.AbstractSet;
import solution.model.Match;
import solution.model.enums.MatchResult;
import solution.model.enums.SetResult;
import solution.model.enums.SetType;
import solution.utils.configs.ApplicationContextProvider;
import solution.utils.logging.Event;
import solution.utils.logging.EventLogger;

import java.util.List;

@Component
public class ConverterImpl implements Converter {

    private int balance = 0;
    private EventLogger logger;
    private ApplicationContext context;

    @Autowired
    public ConverterImpl(
            @Qualifier("cacheFileEventLogger") EventLogger logger
    ) {
        this.context = ApplicationContextProvider.getContext();
        this.logger = logger;
    }

    public MatchResult defineResult(Match match) {
        MatchResult result = match.getMatchResult();
        logEvent("Result is " + result);
        if (result != MatchResult._2_2) {
            return result;
        }
        List<AbstractSet> sets = match.getSets();
        for (AbstractSet set : sets) {
            SetResult setResult = set.getResult();
            if (setResult != SetResult.DRAW) {
                updateBalance(set);
            }
        }
        logEvent("Balance = " + balance);
        MatchResult matchResult = defineConcreteBalance();
        logEvent("Final score = " + matchResult);
        return matchResult;
    }

    private void logEvent(String message) {
        Event event = context.getBean(Event.class);
        event.setMessage(message);
        logger.logEvent(event);
    }

    private MatchResult defineConcreteBalance() {
        if (balance > 0) {
            return MatchResult._25_15;
        } else if (balance == 0) {
            return MatchResult._2_2;
        } else {
            return MatchResult._15_25;
        }
    }

    private void updateBalance(AbstractSet set) {
        SetResult setResult = set.getResult();
        SetType setType = set.getSetType();
        int points = set.toTennisPoints();
        if (setType == SetType.CHESS) {
            if (setResult == SetResult._1_0) {
                balance -= points;
            } else if (setResult == SetResult._0_1) {
                balance += points;
            }
        } else if (setType == SetType.TABLE_TENNIS) {
            if (setResult == SetResult._1_0) {
                balance -= points;
            } else if (setResult == SetResult._0_1) {
                balance += points;
            }
        }
    }
}
