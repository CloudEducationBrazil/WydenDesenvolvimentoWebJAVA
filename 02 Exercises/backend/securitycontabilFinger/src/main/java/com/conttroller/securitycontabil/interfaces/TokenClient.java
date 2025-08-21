package com.conttroller.securitycontabil.interfaces;

import com.conttroller.securitycontabil.dto.TokenEnvioApiContabilidadeDTO;
import com.conttroller.securitycontabil.dto.TokenRetornoApiContabilidadeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "tokenClient", url = "https://conttroller.net/api/d3")
public interface TokenClient {

    @PostMapping(value = "/token", consumes = MediaType.APPLICATION_JSON_VALUE)
    TokenRetornoApiContabilidadeDTO postToken(@RequestBody TokenEnvioApiContabilidadeDTO dto);
}