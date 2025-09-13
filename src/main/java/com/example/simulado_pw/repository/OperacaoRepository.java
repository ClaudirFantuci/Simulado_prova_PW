package com.example.simulado_pw.repository;

import com.example.simulado_pw.model.Operacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperacaoRepository extends JpaRepository<Operacao, Long> {
}
