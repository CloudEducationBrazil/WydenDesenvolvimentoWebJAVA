package com.conttroller.securitycontabil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.conttroller.securitycontabil.dto.TokenEnvioApiContabilidadeDTO;
import com.conttroller.securitycontabil.services.AppContextService;
import com.conttroller.securitycontabil.services.TokenService;
import org.springframework.beans.factory.annotation.Value;

@EnableFeignClients(basePackages = "com.conttroller.securitycontabil.interfaces")
@EnableScheduling
@SpringBootApplication
public class SecuritycontabilApplication implements CommandLineRunner {
	
    @Autowired
    private TokenService tokenService;
    
    @Autowired
    private AppContextService contextService;
    
    @Value("${cnpj.default:}")
    private String defaultCnpj;   
    
	public static void main(String[] args) {

        /*i f (args.length == 0 || args[0].isEmpty()) {
            System.err.println("CNPJ não informado. Uso: java -jar app.jar <cnpj>");
            System.exit(1);
        } */
        
		SpringApplication.run(SecuritycontabilApplication.class, args);
	}

    @Override
    public void run(String... args) {
        String cnpj = (args.length > 0 && !args[0].isBlank()) ? args[0] : defaultCnpj; // 04585532000199 DaxOil

        if (cnpj != null && !cnpj.trim().isEmpty()) {
          contextService.setCnpj(cnpj);
        
           TokenEnvioApiContabilidadeDTO body = new TokenEnvioApiContabilidadeDTO(cnpj, "");
           tokenService.postTokenContabilidade(body);
        }
        else {
            System.err.println(">>> CNPJ não informado! Uso: java -jar app.jar <cnpj>");
        }
    }
}