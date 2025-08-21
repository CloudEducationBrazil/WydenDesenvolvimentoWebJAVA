package com.conttroller.securitycontabil.services;

import org.ini4j.Ini;
import org.springframework.stereotype.Service;

import com.conttroller.securitycontabil.dto.TokenEnvioApiContabilidadeDTO;

import java.io.File;
import java.io.IOException;

@Service
public class IniService {

    public TokenEnvioApiContabilidadeDTO carregarEmpresaDoIni(String caminhoArquivo) {
        try {
            Ini ini = new Ini(new File(caminhoArquivo));
            
            String cnpj = ini.get("empresa", "cnpj");

            TokenEnvioApiContabilidadeDTO dto = new TokenEnvioApiContabilidadeDTO();
            dto.setCnpj(cnpj);
            
            return dto;

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo INI: " + e.getMessage());
            return null;
        }
    }
}