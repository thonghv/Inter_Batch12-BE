package com.example.demoo.configure;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class AppConfig {
    @Bean
    public SessionFactory getSessionFactory() {
        org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration()
                .configure(new File("hibernate.cfg.xml"));
        return cfg.buildSessionFactory();
    }
}
