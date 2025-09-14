package com.example.simulado_pw.controller;

import com.example.simulado_pw.model.Operacao;
import com.example.simulado_pw.service.OperacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operacao")
public class OperacaoController {
    @Autowired
    private OperacaoService service;

    @PostMapping
    public Operacao criar(@Valid @RequestBody Operacao operacao) {
        return service.salvar(operacao);
    }

    @PutMapping
    public Operacao alterarDescricao(@RequestBody Operacao operacao) {
        return service.alterar(operacao);
    }
    @PutMapping("/estorno")
    public Operacao estorno(@RequestBody Operacao operacao) {
        return service.estorno(operacao);
    }
    @GetMapping
    public List<Operacao> buscarTodas(){
        return service.listarTodas();
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);

    }

}
