package com.example.simulado_pw.model;


import com.example.simulado_pw.enums.TipoOpercacao;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name="operacao")
public class Operacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private TipoOpercacao tipo;

    private String job;

    @Positive(message = "valor deve ser positivo")
    private int quantCpu;

    @Positive(message = "valor deve ser positivo")
    private double mem;

    private String descricao;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime CriadoEm = LocalDateTime.now();



}
