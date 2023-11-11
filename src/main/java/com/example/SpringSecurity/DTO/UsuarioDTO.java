package com.example.SpringSecurity.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class UsuarioDTO {

    private Long id;


    private String login;


    private String senha;
}
