package com.conttroller.securitycontabil.components;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "dev.mode", havingValue = "true")
public class NoOpAppTerminator implements AppTerminator {

    @Override
    public void exit(int status) {
        System.out.println("[NoOpAppTerminator] Ignorando exit(" + status + ") porque está em dev/test mode");
    }
}