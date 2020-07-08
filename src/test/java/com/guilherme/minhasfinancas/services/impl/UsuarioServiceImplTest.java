package com.guilherme.minhasfinancas.services.impl;

import com.guilherme.minhasfinancas.exception.RegraNegocioException;
import com.guilherme.minhasfinancas.model.entity.Usuario;
import com.guilherme.minhasfinancas.repositories.UsuarioRepository;
import com.guilherme.minhasfinancas.services.UsuarioService;
import org.junit.Test.None;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UsuarioServiceImplTest {
    @Autowired
    UsuarioService usuarioService;

    @Autowired
    UsuarioRepository usuarioRepository;
    @Test
    public void deveValidarEmail(){
        usuarioRepository.deleteAll();
        usuarioService.validarEmail("email@gmail.com");
    }

}