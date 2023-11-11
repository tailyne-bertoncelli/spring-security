package com.example.SpringSecurity.Repository;

import com.example.SpringSecurity.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByLogin (String login);

}
