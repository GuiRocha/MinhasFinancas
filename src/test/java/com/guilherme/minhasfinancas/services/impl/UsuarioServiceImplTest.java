package com.guilherme.minhasfinancas.services.impl;

import com.guilherme.minhasfinancas.exception.RegraNegocioException;
import com.guilherme.minhasfinancas.model.entity.Usuario;
import com.guilherme.minhasfinancas.repositories.UsuarioRepository;
import com.guilherme.minhasfinancas.services.UsuarioService;
import org.junit.Test.None;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class UsuarioServiceImplTest {

    UsuarioService usuarioService;
    @MockBean
    UsuarioRepository usuarioRepository;

    @BeforeEach
    public void setUp(){
        usuarioService = new UsuarioServiceImpl(usuarioRepository);
    }

    @Test
    public void deveValidarEmail(){
        Mockito.when(usuarioRepository
                .existsByEmail(Mockito.anyString()))
                .thenReturn(false);
        usuarioService.validarEmail("email@gmail.com");
    }
    @Test
    void deveLancarErroAoValidarEmailsQuandoExistirCadastro(){
        try {
            Mockito.when(usuarioRepository
                    .existsByEmail(Mockito.anyString()))
                    .thenReturn(true);
        }catch (RegraNegocioException regraNegocioException){
            usuarioService.validarEmail("email@gmail.com");
        }
    }
}