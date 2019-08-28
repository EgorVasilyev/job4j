package ru.job4j.web.config;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/ads").setViewName("ads");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/createAd").setViewName("createAd");
        registry.addViewController("/users").setViewName("users");
        registry.addViewController("/updateAd").setViewName("updateAd");
        registry.addViewController("/updateUser").setViewName("updateUser");
        registry.addViewController("/userAds").setViewName("userAds");
    }
}
