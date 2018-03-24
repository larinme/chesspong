package solution.utils.logging.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import solution.utils.logging.Event;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CacheFileEventLogger extends FileEventLogger {

    private List<Event> cache;
    private int cacheSize;

    @Autowired
    public CacheFileEventLogger(
            @Value("${log_output_file}") String filename,
            @Value("#{T(java.lang.Integer).parseInt(${log_cache_size})}")  Integer cacheSize) {
        super(filename);
        this.cacheSize = cacheSize;
        cache = new ArrayList<Event>(cacheSize);
    }

    @Override
    public void logEvent(Event event) {
        if (cache.size() + 1 == cacheSize){
            cache.add(event);
            flushEvents();
            cache.clear();
        } else {
            cache.add(event);
        }
    }

    @PostConstruct
    @Override
    protected void init() throws IOException {
        super.init();
    }

    @PreDestroy
    protected void destroy(){
        flushEvents();
    }

    private void flushEvents() {
        for (Event eventCache : cache) {
            super.logEvent(eventCache);
        }
    }
}
