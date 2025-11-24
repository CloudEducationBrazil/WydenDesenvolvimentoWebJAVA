package com.conttroller.securitycontabil.components;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.boot.SpringApplication;

@Component
public class ApplicationContextProvider {

    private final ApplicationContext applicationContext;

    public ApplicationContextProvider(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void exitApplication(int exitCode) {
        SpringApplication.exit(applicationContext, () -> exitCode);
    }
}