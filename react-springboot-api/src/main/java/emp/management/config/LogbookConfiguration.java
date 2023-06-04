package emp.management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.core.DefaultHttpLogWriter;
import org.zalando.logbook.core.DefaultSink;
import org.zalando.logbook.core.HeaderFilters;
import org.zalando.logbook.json.JsonHttpLogFormatter;

import static org.zalando.logbook.core.Conditions.*;

@Configuration
public class LogbookConfiguration {

    private static final String[] HEADERS_FIELDS = {"vary", "connection", "keep-alive", "transfer-encoding"};
    @Bean
    public Logbook logbook() {
        return Logbook.builder()
                .condition(exclude(
                        requestTo("/actuator/**"),
                        requestTo("/admin/**"),
                        requestTo("/swagger-ui/**"),
                        requestTo("/v3/api-docs/**"),
                        contentType(MediaType.APPLICATION_OCTET_STREAM_VALUE)))
                .headerFilter(HeaderFilters.removeHeaders(HEADERS_FIELDS))
                .sink(new DefaultSink(new JsonHttpLogFormatter(), new DefaultHttpLogWriter()))
                .build();
    }
}