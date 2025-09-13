package com.example.simulado_pw.service;

import com.example.simulado_pw.model.Operacao;
import com.example.simulado_pw.repository.OperacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OperacaoService {

    @Autowired
    private OperacaoRepository repository;

    public Operacao salvar(Operacao operacao) {
        return repository.save(operacao);
    }

    public Operacao alterar(Operacao operacao) {
        Operacao operacaoSalva = repository.findById(operacao.getId())
                .orElseThrow(() -> new NoSuchElementException("Não encontrado"));
        operacaoSalva.setDescricao(operacao.getDescricao());
        return repository.save(operacaoSalva);
    }

    public List<Operacao> listarTodas(){
        return repository.findAll();
    }
}
