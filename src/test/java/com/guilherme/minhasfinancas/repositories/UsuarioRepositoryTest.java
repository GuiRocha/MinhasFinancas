package com.guilherme.minhasfinancas.repositories;

import com.guilherme.minhasfinancas.model.entity.Usuario;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioRepositoryTest {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    TestEntityManager testEntityManager;

    @Test
    void deveVerificarExistenciaDeUmEmail() {
        Usuario usuario = Usuario.builder().nome("guilherme").email("guilhermerxcha@gmial.com").senha("123456").build();
        testEntityManager.persist(usuario);
        boolean result = usuarioRepository.existsByEmail("guilhermerxcha@gmial.com");
        assertThat(result).isTrue();
    }

    @Test
    void devedeveRetornarFalsoQuandoNaoHouverusuarioCadastrado() {
        boolean result = usuarioRepository.existsByEmail("guilhermer@gmail");
        assertThat(result).isFalse();
    }
}