package com.acme.cars.service;

import com.acme.cars.model.Usuario;
import com.acme.cars.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    /**
     * Busca todos os usuários cadastrados.
     *
     * @return Lista de usuários
     */
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    /**
     * Busca um usuário pelo email.
     *
     * @param email Email do usuário
     * @return Optional do usuário encontrado
     */
    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    /**
     * Cadastra um novo usuário.
     *
     * @param usuario Objeto Usuario a ser salvo
     * @return Usuario cadastrado
     */
    public Usuario cadastrarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
