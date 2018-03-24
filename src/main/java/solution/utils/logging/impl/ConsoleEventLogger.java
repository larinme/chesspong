package solution.utils.logging.impl;

import org.springframework.stereotype.Component;
import solution.utils.logging.Event;
import solution.utils.logging.EventLogger;

@Component
public class ConsoleEventLogger implements EventLogger {

    public void logEvent(Event event) {
        System.out.println(event);
    }
}
