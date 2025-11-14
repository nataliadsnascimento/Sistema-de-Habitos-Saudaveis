package com.habitos.sistema_habitos_saudaveis.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Habito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String tipo; // ex: "Sono" "Exercicios" e coisas do tipo.
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
