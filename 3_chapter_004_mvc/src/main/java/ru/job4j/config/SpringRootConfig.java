package ru.job4j.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan("ru.job4j")
@ImportResource("classpath*:hibernate-context.xml")
public class SpringRootConfig {
}