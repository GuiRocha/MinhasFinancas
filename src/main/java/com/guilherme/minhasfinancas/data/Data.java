package com.guilherme.minhasfinancas.data;

import com.guilherme.minhasfinancas.model.entity.Usuario;
import com.guilherme.minhasfinancas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.Arrays;

public class Data implements CommandLineRunner {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        usuarioRepository.deleteAll();
        Usuario usuarioGuilherme = Usuario.builder()
                .nome("Guilherme Rocha Ramos")
                .email("Guilhermerxcha@gmail.com")
                .senha("123456").build();
        usuarioRepository.saveAll(Arrays.asList(usuarioGuilherme));
    }
}
