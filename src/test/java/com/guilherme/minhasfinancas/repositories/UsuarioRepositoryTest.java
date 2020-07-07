package com.guilherme.minhasfinancas.repositories;

import com.guilherme.minhasfinancas.model.entity.Usuario;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UsuarioRepositoryTest {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Test
    void deveVerificarExistenciaDeUmEmail() {
        Usuario usuario = Usuario.builder().nome("guilherme").email("guilhermerxcha@gmial.com").senha("123456").build();
        usuarioRepository.save(usuario);

       boolean result = usuarioRepository.existsByEmail("guilhermerxcha@gmial.com");

        assertThat(result).isTrue();
    }
}