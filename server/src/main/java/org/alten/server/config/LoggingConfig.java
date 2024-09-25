package org.alten.server.config;

import org.alten.tracing.handler.LoggingHandler;
import org.alten.tracing.handler.impl.DefaultLoggingHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingConfig {
    @Bean
    public LoggingHandler loggingHandler() {
        return new DefaultLoggingHandler();
    }
}
