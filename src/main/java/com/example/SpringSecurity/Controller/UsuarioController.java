package com.example.SpringSecurity.Controller;

import com.example.SpringSecurity.DTO.UsuarioDTO;
import com.example.SpringSecurity.Service.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService service;

    @GetMapping
    public ResponseEntity<UsuarioDTO> findById(@RequestParam final Long id){
        try {
            return ResponseEntity.ok(this.service.findById(id));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: " + e.getMessage(), e);
        }
    }

    @GetMapping("logado")
    public String administrador(){
        return "Acessos administrador Logado";
    }

    @GetMapping("/listas")
    public ResponseEntity<List<UsuarioDTO>> findAll(){
        try {
            return ResponseEntity.ok(this.service.findAll());
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: " + e.getMessage(), e);
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> insert(@Validated @RequestBody UsuarioDTO usuarioDTO)
    {
        try {
            this.service.cadastrar(usuarioDTO);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestParam Long id ,@RequestBody @Valid final UsuarioDTO usuarioDTO){
        try {
            this.service.alterar(id, usuarioDTO);
            return ResponseEntity.ok("Registro editado com sucesso");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
