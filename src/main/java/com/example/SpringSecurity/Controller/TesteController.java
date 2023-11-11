package com.example.SpringSecurity.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste-controller")
public class TesteController {
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public String teste(){
        return "<h1> Teste </h1>";
    }

    @GetMapping("/livre")
    public String rotaLivre(){
        return "<h2> Rota Livre </h2>";
    }
}
