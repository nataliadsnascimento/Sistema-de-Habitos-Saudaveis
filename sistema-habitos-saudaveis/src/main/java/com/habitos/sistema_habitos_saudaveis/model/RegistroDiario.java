package com.habitos.sistema_habitos_saudaveis.model;

import lombok.Data;
import java.time.LocalDate;

// REMOVIDO: import jakarta.persistence.*;
// REMOVIDO: @Entity

@Data
public class RegistroDiario {

    // REMOVIDO: @Id, @GeneratedValue
    private Long id;

    private LocalDate data; // Atributo definido no guia
    private String observacao; // Atributo definido no guia

    // Relação simples: Referência ao Hábito
    // REMOVIDO: @ManyToOne, @JoinColumn
    private Habito habito;

    // Campo auxiliar para buscas no JSON (não faz parte do guia, mas é útil)
    private Long usuarioId;
}