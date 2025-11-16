package com.habitos.sistema_habitos_saudaveis.model;

import lombok.Data;

@Data
public class Usuario {
    private Long id;
    private String nome;
    private String email;
    private int idade;

    // ATRIBUTOS ADICIONADOS PARA COMPILAR A LÓGICA DE CALCULAR IMC:
    private double peso;
    private double altura;
}