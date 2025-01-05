package com.acme.cars.repository;

import com.acme.cars.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Busca um usuário pelo email.
     *
     * @param email Email do usuário
     * @return Optional do usuário encontrado
     */
    Optional<Usuario> findByEmail(String email);
}
