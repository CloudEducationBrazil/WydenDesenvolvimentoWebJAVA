package com.conttroller.securitycontabil.components;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "dev.mode", havingValue = "false", matchIfMissing = true)
public class RealAppTerminator implements AppTerminator {

    private final ApplicationContextProvider contextProvider;

    public RealAppTerminator(ApplicationContextProvider contextProvider) {
        this.contextProvider = contextProvider;
    }

    @Override
    public void exit(int status) {
        contextProvider.exitApplication(status);
    }
}