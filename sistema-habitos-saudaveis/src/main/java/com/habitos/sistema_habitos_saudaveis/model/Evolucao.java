package com.habitos.sistema_habitos_saudaveis.model;

import lombok.Data;

@Data
public class Evolucao {
    private String meta;
    private double progresso;

    // NOVO CAMPO: Total de hábitos registrados no período
    private long totalRegistros;

    public Evolucao(String meta, double progresso, long totalRegistros) {
        this.meta = meta;
        this.progresso = progresso;
        this.totalRegistros = totalRegistros;
    }
}