package com.guilherme.minhasfinancas.services.impl;

import com.guilherme.minhasfinancas.exception.ErroAutenticacao;
import com.guilherme.minhasfinancas.exception.RegraNegocioException;
import com.guilherme.minhasfinancas.model.entity.Usuario;
import com.guilherme.minhasfinancas.repositories.UsuarioRepository;
import com.guilherme.minhasfinancas.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public Usuario autenticar(String email, String senha) {
        Optional<Usuario> usuario = usuarioRepository.findAllByEmail(email);

        if (!usuario.isPresent()) {
            throw new ErroAutenticacao("Usuario não encontrado para o email informado");
        }
        if (!usuario.get().getSenha().equals(senha)) {
            throw new ErroAutenticacao("Senha Invalida");
        }
        return usuario.get();
    }

    @Override
    @Transactional
    public Usuario salvarUsuario(Usuario usuario) {
        validarEmail(usuario.getEmail());

        return usuarioRepository.save(usuario);
    }

    @Override
    public void validarEmail(String email) {
        boolean existe = usuarioRepository.existsByEmail(email);
        if (existe) {
            throw new RegraNegocioException("Já existe um email cadastrado com esse email");
        }
    }
    @Override 
    public Optional<Usuario> obterPorId(Long id){
       return usuarioRepository.findById(id);
    }
}
