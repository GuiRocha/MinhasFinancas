package com.guilherme.minhasfinancas.services.impl;

import com.guilherme.minhasfinancas.exception.RegraNegocioException;
import com.guilherme.minhasfinancas.model.entity.Lancamento;
import com.guilherme.minhasfinancas.model.entity.Usuario;
import com.guilherme.minhasfinancas.model.enums.StatusLancamento;
import com.guilherme.minhasfinancas.repositories.LancamentoRepository;
import com.guilherme.minhasfinancas.services.LancamentoService;
import com.guilherme.minhasfinancas.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.lang.StackWalker.Option;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LancamentoServiceImpl implements LancamentoService {
    @Autowired
    LancamentoRepository lancamentoRepository;

    @Override
    @Transactional
    public Lancamento salvar(Lancamento lancamento) {
        validar(lancamento);
        lancamento.setStatusLancamento(StatusLancamento.PENDENTE);
        return lancamentoRepository.save(lancamento);
    }

    @Override
    @Transactional
    public Lancamento atualizar(Lancamento lancamento) {
        Objects.requireNonNull(lancamento.getId());
        validar(lancamento);
        return lancamentoRepository.save(lancamento);
    }

    @Override
    @Transactional
    public void deletar(Lancamento lancamento) {
        Objects.requireNonNull(lancamento.getId());
        lancamentoRepository.delete(lancamento);
    }

    @Override
    @Transactional
    public List<Lancamento> buscar(Lancamento lancamentoFiltro) {
        Example example = Example.of(lancamentoFiltro,
                ExampleMatcher.matching()
                        .withIgnoreCase()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
        return lancamentoRepository.findAll(example);
    }

    @Override
    @Transactional
    public void atualizarStatus(Lancamento lancamento, StatusLancamento status) {
        lancamento.setStatusLancamento(status);
        atualizar(lancamento);
    }

    @Override
    public void validar(Lancamento lancamento) {
        if (lancamento.getDescricao() == null || lancamento.getDescricao().trim().equals("")){
            throw new RegraNegocioException("Informe uma descrição válida");
        }
        if (lancamento.getMes() == null || lancamento.getMes() < 1 || lancamento.getMes() > 12){
            throw new RegraNegocioException("Informe um mês válida");
        }
        if (lancamento.getAno() == null || lancamento.getAno().toString().length() != 4 ){
            throw new RegraNegocioException("Informe um ano válida");
        }
        if (lancamento.getUsuario() == null || lancamento.getUsuario().getId() == null){
            throw new RegraNegocioException("Informe um usuario");
        }
        if (lancamento.getValor() ==  null || lancamento.getValor().compareTo(BigDecimal.ZERO) < 1){
            throw new RegraNegocioException("Informe um valor válido");
        }
        if(lancamento.getTipoLancamento() == null){
            throw new RegraNegocioException("Informe um tipo de lançamento");
        }
    }

    @Override
    public Optional<Lancamento> obterPorId(Long id) {
        return lancamentoRepository.findById(id);
    }

}
