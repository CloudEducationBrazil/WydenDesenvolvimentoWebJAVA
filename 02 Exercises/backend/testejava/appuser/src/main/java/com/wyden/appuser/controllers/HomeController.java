package com.wyden.appuser.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index"; // Nome do arquivo HTML em templates
    }
    
    @PostMapping("/processar")
    public String processarFormulario(@RequestParam String nome,
                                      @RequestParam int idade,
                                      Model model) {
        String mensagem;
        if (idade >= 18) {
            mensagem = "Olá " + nome + ", você é maior de idade.";
        } else {
            mensagem = "Olá " + nome + ", você é menor de idade.";
        }
        // Envia a mensagem para a página resultado
        model.addAttribute("mensagem", mensagem);
        return "resultado";
    }
}