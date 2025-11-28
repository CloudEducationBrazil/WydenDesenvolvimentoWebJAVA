package com.wyden.appusermodelattribute.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.wyden.appusermodelattribute.entities.User;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        // Cria um User vazio para o formulário
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping("/processar")
    public String processarFormulario(@ModelAttribute User user, Model model) {
        String mensagem;
        if (user.getIdade() >= 18) {
            mensagem = "Olá " + user.getNome() + ", você é maior de idade.";
        } else {
            mensagem = "Olá " + user.getNome() + ", você é menor de idade.";
        }

        model.addAttribute("mensagem", mensagem);
        return "resultado";
    }
}