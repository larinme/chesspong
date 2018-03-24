package solution.utils.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.text.DateFormat;
import java.util.Date;

@Configuration
public class DateConfigs {

    @Bean
    @Scope("prototype")
    public Date getDate(){
        return new Date();
    }

    @Bean
    public DateFormat getDateFormat(){
        return DateFormat.getDateTimeInstance();
    }
}
