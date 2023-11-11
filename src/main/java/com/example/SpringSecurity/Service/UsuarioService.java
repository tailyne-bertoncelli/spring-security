package com.example.SpringSecurity.Service;

import com.example.SpringSecurity.DTO.UsuarioDTO;
import com.example.SpringSecurity.Entity.Usuario;
import com.example.SpringSecurity.Repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.SpringSecurity.config.message.handling.ErrorMessages;


import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioDTO findById(Long id){

        Usuario usuario = this.repository.findById(id).orElseThrow(()-> {
            throw new EntityNotFoundException(ErrorMessages.ENTIDADE_NAO_ENCONTRADA);
        });

        return modelMapper.map(usuario, UsuarioDTO.class);
    }


    public List<UsuarioDTO> findAll(){
        List<Usuario> usuarios = this.repository.findAll();
        List<UsuarioDTO> usuarioDTOS = new ArrayList<>();
        for (Usuario i : usuarios){
            usuarioDTOS.add(modelMapper.map(i,UsuarioDTO.class));
        }
        return usuarioDTOS;
    }
/*
    public void cadastrar(UsuarioDTO usuarioDTO){
        this.repository.save(modelMapper.map(usuarioDTO, Usuario.class));
    }*/
    public void cadastrar(UsuarioDTO usuarioDTO){
        // Encode the password before saving to the database
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        this.repository.save(modelMapper.map(usuarioDTO, Usuario.class));
    }

    public void alterar(Long id, UsuarioDTO  usuarioDTO){
        Usuario usuario = this.repository.findById(id).orElseThrow(() ->
                new RuntimeException("Registro não encontrado"));
        modelMapper.map(usuarioDTO, usuario);
        this.repository.save(usuario);
    }

   /* public void delete (Long id){
        Usuario usuario = this.repository.findById(id).orElseThrow(()->
                new RuntimeException("Registro não encontrado"));
        usuario.setAtivo(false);

    }*/

}
