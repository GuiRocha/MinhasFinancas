package com.guilherme.minhasfinancas.repositories;

import com.guilherme.minhasfinancas.model.entity.Usuario;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioRepositoryTest {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    TestEntityManager testEntityManager;

    public Usuario data() {
        Usuario usuario = Usuario.builder()
                .nome("guilherme")
                .email("guilhermerxcha@gmial.com")
                .senha("123456")
                .build();
        return usuario;
    }

    @Test
    void deveVerificarExistenciaDeUmEmail() {
        Usuario usuario = data();
        testEntityManager.persist(usuario);
        boolean result = usuarioRepository.existsByEmail("guilhermerxcha@gmial.com");
        assertThat(result).isTrue();
    }

    @Test
    void devedeveRetornarFalsoQuandoNaoHouverusuarioCadastrado() {
        boolean result = usuarioRepository.existsByEmail("guilhermerxcha@gmial.com");
        assertThat(result).isFalse();
    }

    @Test
    void devePersistirUsuarioNaBaseDeDados() {
        Usuario usuario = data();
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        assertThat(usuarioSalvo.getId()).isNotNull();
    }
    @Test
    void deveBuscarUsuarioPorEmail(){
        Usuario usuario = data();
        testEntityManager.persist(usuario);
        Optional<Usuario> result = usuarioRepository.findAllByEmail("guilhermerxcha@gmial.com");
        assertThat(result.isPresent()).isTrue();
    }
    @Test
    void deveRetornarVazioAoBuscarUsuarioPorEmail(){
        Optional<Usuario> result = usuarioRepository.findAllByEmail("guilhermerxcha@gmial.com");
        assertThat(result.isPresent()).isFalse();
    }
}