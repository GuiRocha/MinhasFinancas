package com.guilherme.minhasfinancas.services.impl;

import com.guilherme.minhasfinancas.exception.RegraNegocioException;
import com.guilherme.minhasfinancas.model.entity.Usuario;
import com.guilherme.minhasfinancas.repositories.UsuarioRepository;
import com.guilherme.minhasfinancas.services.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class UsuarioServiceImplTest {

    UsuarioService usuarioService;
    @MockBean
    UsuarioRepository usuarioRepository;

    @BeforeEach
    public void setUp() {
        usuarioService = new UsuarioServiceImpl(usuarioRepository);
    }

    @Test
    public void deveValidarEmail() {
        Mockito.when(usuarioRepository
                .existsByEmail(Mockito.anyString()))
                .thenReturn(false);
        usuarioService.validarEmail("email@gmail.com");
    }
    @Test
    void deveAutenticarUsuarioComSucesso(){
        String email = "guilhermerxcha@gmail.com";
        String senha = "123456";

        Usuario usuario = Usuario.builder().email(email).senha(senha).build();

        Mockito.when(usuarioRepository.findAllByEmail(email)).thenReturn(Optional.of(usuario));

        Usuario result = usuarioService.autenticar(email, senha);

        assertThat(result).isNotNull();

    }
    @Test
    void deveLancarErroAoValidarEmailsQuandoExistirCadastro() {
        try {
            Mockito.when(usuarioRepository
                    .existsByEmail(Mockito.anyString()))
                    .thenReturn(true);
        } catch (RegraNegocioException regraNegocioException) {
            usuarioService.validarEmail("email@gmail.com");
        }
    }
    @Test
    void deveLancarErroAoNaoEncontrarUsuarioCadastrado() {
        try {
            Mockito.when(usuarioRepository
                    .existsByEmail(Mockito.anyString()))
                    .thenReturn(false);
        } catch (RegraNegocioException regraNegocioException) {
            usuarioService.validarEmail("email@gmail.com");
        }
    }
    @Test
    void deveLancarErroQuandoSenhaIncorreta() {
        String senha = "12345678";
        Usuario usuario = Usuario.builder().email("guilhermerxcha@gmail.com").senha(senha).build();
        try {
            Mockito.when(usuarioRepository
                    .findAllByEmail(Mockito.anyString()))
                    .thenReturn(Optional.of(usuario));
        } catch (RegraNegocioException regraNegocioException) {
            usuarioService.autenticar("guilhermerxcha@gmail.com", "123");
        }
    }
}