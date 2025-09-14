package com.example.simulado_pw.repository;

import com.example.simulado_pw.enums.TipoOpercacao;
import com.example.simulado_pw.model.Operacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OperacaoRepository extends JpaRepository<Operacao, Long> {

    public List<Operacao> findByTipo(TipoOpercacao tipo);
}
