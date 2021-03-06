package com.guilherme.minhasfinancas.controllers;

import com.guilherme.minhasfinancas.exception.ErroAutenticacao;
import com.guilherme.minhasfinancas.exception.RegraNegocioException;
import com.guilherme.minhasfinancas.model.dto.UsuarioDTO;
import com.guilherme.minhasfinancas.model.entity.Usuario;
import com.guilherme.minhasfinancas.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/autenticar")
    public ResponseEntity autenticar(@RequestBody final UsuarioDTO dto) {

        try {
            final Usuario usuarioAutenticado = usuarioService.autenticar(dto.getEmail(),  dto.getSenha());
            return ResponseEntity.ok(usuarioAutenticado);
        } catch (final ErroAutenticacao erroAutenticacao) {
            return ResponseEntity.badRequest().body(erroAutenticacao.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody final UsuarioDTO dto) {
        final Usuario usuario = Usuario.builder().nome(dto.getNome())
                .email(dto.getEmail())
                .senha(dto.getSenha())
                .build();
        try {
            final Usuario usuarioSalvo = usuarioService.salvarUsuario(usuario);
            return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
        } catch (final RegraNegocioException regraNegocioException) {
            return ResponseEntity.badRequest().body(regraNegocioException.getMessage());
        }
    }

}
