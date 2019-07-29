package ru.job4j.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContextConfiguration {
    @Bean
    public ConfigBean configBean() {
        return new ConfigBean();
    }
}
