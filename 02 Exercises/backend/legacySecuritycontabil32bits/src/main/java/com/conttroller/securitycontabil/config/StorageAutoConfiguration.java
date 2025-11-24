package com.conttroller.securitycontabil.config;

import com.conttroller.securitycontabil.storage.FileTokenStorage;
import com.conttroller.securitycontabil.storage.TokenStorage;
import com.conttroller.securitycontabil.storage.WindowsRegistryTokenStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageAutoConfiguration {

    @Bean
    TokenStorage tokenStorage(WindowsRegistryTokenStorage win, FileTokenStorage file) {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            // prefer registry on Windows
            return win;
        } else {
            return file;
        }
    }
}