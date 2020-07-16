package com.guilherme.minhasfinancas.controllers;

import com.guilherme.minhasfinancas.services.LancamentoService;
import com.guilherme.minhasfinancas.services.UsuarioService;
import com.guilherme.minhasfinancas.exception.RegraNegocioException;
import com.guilherme.minhasfinancas.model.entity.Lancamento;
import com.guilherme.minhasfinancas.model.dto.LancamentoDTO;
import com.guilherme.minhasfinancas.model.entity.Usuario;
import com.guilherme.minhasfinancas.model.enums.StatusLancamento;
import com.guilherme.minhasfinancas.model.enums.TipoLancamento;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.channels.FileChannel;
import java.text.ParseException;
import java.util.Optional;

@RestController
@RequestMapping("api/lancamentos")
public class LancamentoController {
    @Autowired
    private LancamentoService lancamentoService;
    @Autowired
    private UsuarioService usuarioService;


    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity salvar(@RequestBody LancamentoDTO dto){
        try {
            Lancamento entidade = converter(dto);
            entidade = lancamentoService.salvar(entidade);
            return ResponseEntity.ok(entidade);
        }catch (RegraNegocioException  regraNegocioException){
            return ResponseEntity.badRequest().body(regraNegocioException.getMessage());
        }

    }

//    public Lancamento converter(LancamentoDTO dto){
//        Lancamento lancamento = new Lancamento();
//
//        lancamento.setId(dto.getId());
//        lancamento.setDescricao(dto.getDescricao());
//        lancamento.setAno(dto.getAno());
//        lancamento.setMes(dto.getMes());
//        lancamento.setValor(dto.getValor());
//
//        Usuario usuario = usuarioService.obterPorId(dto.getUsuario().getId())
//                .orElseThrow(() -> new RegraNegocioException("Usuario não encontrado para o Id informado"));
//        lancamento.setUsuario(usuario);
//        lancamento.setTipoLancamento(TipoLancamento.valueOf(dto.getTipo()));
//        lancamento.setStatusLancamento(StatusLancamento.valueOf(dto.getStatus()));
//        return lancamento;
//
//    }
    private Lancamento converter(LancamentoDTO lancamentoDTO){
        Lancamento lancamento = modelMapper.map(lancamentoDTO, Lancamento.class);
        Usuario usuario = usuarioService.obterPorId(lancamentoDTO.getUsuario().getId())
                .orElseThrow(() -> new RegraNegocioException("Usuario não encontrado para o Id informado"));
        return lancamento;
    }

}