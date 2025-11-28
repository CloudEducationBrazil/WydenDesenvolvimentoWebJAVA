package com.conttroller.tokencontabilidade.conttrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.conttroller.tokencontabilidade.dto.TokenDTO;
import com.conttroller.tokencontabilidade.services.ContabilService;

@RestController
@RequestMapping(value="/api/tokencontabilidade")
public class TokenController {
	// https://conttroller.net/api/d3/token
    @Autowired
    private ContabilService contabilService;

    @PostMapping
    public ResponseEntity<?> postTokenContabilidade(@RequestBody TokenDTO body){
        return contabilService.postTokenContabilidade(body);
    }
}	