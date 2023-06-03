package emp.management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.zalando.logbook.Logbook;

import static org.zalando.logbook.core.Conditions.*;

public class LogbookConfiguration {

    @Bean
    public Logbook logbook() {
        return Logbook.builder()
                .condition(exclude(contentType(MediaType.APPLICATION_OCTET_STREAM_VALUE)))
                .build();
    }
}