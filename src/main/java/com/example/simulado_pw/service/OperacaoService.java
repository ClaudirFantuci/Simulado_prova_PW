package com.example.simulado_pw.service;

import com.example.simulado_pw.config.ConfiguracaoCluster;
import com.example.simulado_pw.enums.TipoOpercacao;
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
        if (validarOperacao(operacao)) {
            return repository.save(operacao);
        } else {
            throw new IllegalArgumentException("Operação inválida: viola limites do cluster ou contém dados incorretos");
        }
    }

    public Operacao alterar(Operacao operacao) {
        Operacao operacaoSalva = repository.findById(operacao.getId())
                .orElseThrow(() -> new NoSuchElementException("Não encontrado"));
        operacaoSalva.setDescricao(operacao.getDescricao());
        return repository.save(operacaoSalva);
    }
    public Operacao estorno(Operacao operacao) {
        Operacao operacaoSalva = repository.findById(operacao.getId())
                .orElseThrow(() -> new NoSuchElementException("Não encontrado"));
        operacaoSalva.setTipo(operacao.getTipo().equals(TipoOpercacao.ALOCACAO)?TipoOpercacao.LIBERACAO:TipoOpercacao.ALOCACAO);
        operacaoSalva.setJob(operacao.getJob());
        operacaoSalva.setQuantCpu(operacao.getQuantCpu());
        operacaoSalva.setMem(operacao.getMem());
        operacaoSalva.setDescricao(operacao.getDescricao());
        return salvar(operacaoSalva);
    }

    public List<Operacao> listarTodas(){
        return repository.findAll();
    }

    public void excluir(Long id) {
        Operacao operacao = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Operação não encontrada"));
        if (validarExclusao(operacao)) {
            repository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Exclusão inválida: viola limites do cluster");
        }
    }

    public boolean validarOperacao(Operacao operacao) {
        List<Operacao> totalAlocacao = repository.findByTipo(TipoOpercacao.ALOCACAO);
        List<Operacao> totalLiberacao = repository.findByTipo(TipoOpercacao.LIBERACAO);
        double memAlocada = 0;
        int quantCpuAlocada = 0;
        double memLiberada = 0;
        int quantCpuLiberada = 0;
        for (Operacao op : totalAlocacao) {
            memAlocada += op.getMem();
            quantCpuAlocada += op.getQuantCpu();
        }
        for (Operacao op : totalLiberacao) {
            memLiberada += op.getMem();
            quantCpuLiberada += op.getQuantCpu();
        }
        int cpuLivre = ConfiguracaoCluster.CPU_MAX - (quantCpuAlocada - quantCpuLiberada);
        double memLivre = ConfiguracaoCluster.MEM_MAX - (memAlocada - memLiberada);

        if (operacao.getTipo() == TipoOpercacao.ALOCACAO) {
            return cpuLivre >= operacao.getQuantCpu() && memLivre >= operacao.getMem();
        } else if (operacao.getTipo() == TipoOpercacao.LIBERACAO) {
            return (quantCpuAlocada - quantCpuLiberada - operacao.getQuantCpu() >= 0) &&
                    (memAlocada - memLiberada - operacao.getMem() >= 0);
        }

        return false;
    }


    public boolean validarExclusao(Operacao operacao) {
        List<Operacao> totalAlocacao = repository.findByTipo(TipoOpercacao.ALOCACAO);
        List<Operacao> totalLiberacao = repository.findByTipo(TipoOpercacao.LIBERACAO);

        double memAlocada = 0;
        int quantCpuAlocada = 0;
        double memLiberada = 0;
        int quantCpuLiberada = 0;

        for (Operacao op : totalAlocacao) {
            memAlocada += op.getMem();
            quantCpuAlocada += op.getQuantCpu();
        }
        for (Operacao op : totalLiberacao) {
            memLiberada += op.getMem();
            quantCpuLiberada += op.getQuantCpu();
        }

        int cpuTotalAlocado = quantCpuAlocada - quantCpuLiberada;
        double memTotalAlocada = memAlocada - memLiberada;

        if (operacao.getTipo() == TipoOpercacao.ALOCACAO) {
            return (cpuTotalAlocado - operacao.getQuantCpu() >= 0) &&
                    (memTotalAlocada - operacao.getMem() >= 0);
        } else if (operacao.getTipo() == TipoOpercacao.LIBERACAO) {
            return (cpuTotalAlocado + operacao.getQuantCpu() <= ConfiguracaoCluster.CPU_MAX) &&
                    (memTotalAlocada + operacao.getMem() <= ConfiguracaoCluster.MEM_MAX);
        }

        return false;
    }
}
