package com.conttroller.securitycontabil.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.conttroller.securitycontabil.services.TokenExecutorService;

import javax.mail.internet.MimeMessage;
import org.springframework.mail.MailException;

import java.util.Properties;

@Configuration
public class MailConfig {
    private static final Logger logger = LoggerFactory.getLogger(TokenExecutorService.class);

    @Bean
    JavaMailSender javaMailSender(
            @Value("${spring.mail.username}") String username,
            @Value("${spring.mail.password}") String password) {

        return new FallbackJavaMailSender(username, password);
    }

    /**
     * JavaMailSender customizado que tenta STARTTLS 587 e faz fallback para SSL 465 se necessário
     */
    private static class FallbackJavaMailSender extends JavaMailSenderImpl {

        private final JavaMailSenderImpl fallback;

        public FallbackJavaMailSender(String username, String password) {
            // Configuração principal: STARTTLS 587
            setHost("smtp.gmail.com");
            setPort(587);
            setUsername(username);
            setPassword(password);

            Properties props = getJavaMailProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            props.put("mail.debug", "false");
            props.put("mail.smtp.connectiontimeout", "10000");
            props.put("mail.smtp.timeout", "10000");
            props.put("mail.smtp.writetimeout", "10000");

            // Configuração de fallback: SSL 465
            fallback = new JavaMailSenderImpl();
            fallback.setHost("smtp.gmail.com");
            fallback.setPort(465);
            fallback.setUsername(username);
            fallback.setPassword(password);

            Properties fallbackProps = fallback.getJavaMailProperties();
            fallbackProps.put("mail.transport.protocol", "smtp");
            fallbackProps.put("mail.smtp.auth", "true");
            fallbackProps.put("mail.smtp.ssl.enable", "true");
            fallbackProps.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            fallbackProps.put("mail.debug", "true");
            fallbackProps.put("mail.smtp.connectiontimeout", "10000");
            fallbackProps.put("mail.smtp.timeout", "10000");
            fallbackProps.put("mail.smtp.writetimeout", "10000");
        }

        @Override
        public void send(MimeMessage mimeMessage) throws MailException  {
            try {
                super.send(mimeMessage);
            } catch (Exception e) {
            	logger.error("Falha no envio STARTTLS 587, tentando fallback SSL 465..." + e.getMessage());
                //System.out.println("Falha no envio STARTTLS 587, tentando fallback SSL 465...");
                fallback.send(mimeMessage);
            }
        }
    }
}