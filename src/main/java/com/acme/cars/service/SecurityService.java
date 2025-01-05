package com.acme.cars.service;

import com.acme.cars.dto.AuthUserDTO;
import com.acme.cars.exception.AuthenticationException;
import com.acme.cars.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final UsuarioService usuarioService;
    private final TokenService tokenService;

    /**
     * Método para autenticar um usuário com base em email e senha.
     *
     * @param authUserDTO DTO contendo email e senha do usuário
     * @return Token gerado para o usuário autenticado
     * @throws AuthenticationException se as credenciais forem inválidas
     */
    public String authenticate(AuthUserDTO authUserDTO) throws AuthenticationException {
        // Busca o usuário pelo email
        Optional<Usuario> optionalUsuario = usuarioService.findByEmail(authUserDTO.getEmail());

        if (optionalUsuario.isEmpty()) {
            throw new AuthenticationException("Usuário ou senha incorretos");
        }

        Usuario usuario = optionalUsuario.get();

        // Valida a senha
        if (usuario.getPassword().equals(authUserDTO.getPassword())) {
            // Gera e retorna o token para o usuário
            return tokenService.generateToken(usuario);
        } else {
            throw new AuthenticationException("Usuário ou senha incorretos");
        }
    }
}
