package com.guilherme.minhasfinancas.repositories;

import com.guilherme.minhasfinancas.model.entity.Lancamento;
import com.guilherme.minhasfinancas.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
}
