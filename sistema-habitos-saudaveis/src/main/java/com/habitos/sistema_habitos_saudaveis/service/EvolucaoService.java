package com.habitos.sistema_habitos_saudaveis.service;

import com.habitos.sistema_habitos_saudaveis.model.Evolucao;
import com.habitos.sistema_habitos_saudaveis.model.RegistroDiario;
import com.habitos.sistema_habitos_saudaveis.repository.RegistroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class EvolucaoService {

    @Autowired
    private RegistroRepository registroRepository;

    public Evolucao gerarRelatorio(Long usuarioId, LocalDate dataInicio, LocalDate dataFim) {

        List<RegistroDiario> registrosEncontrados =
                registroRepository.findByUsuarioIdAndDataBetween(usuarioId, dataInicio, dataFim);

        // --- CÁLCULO REAL DE PROGRESSO ---

        // Total de registros encontrados (valor que você calculou como 4)
        long totalRegistros = registrosEncontrados.size();

        // Número de dias no período
        long totalDiasNoPeriodo = ChronoUnit.DAYS.between(dataInicio, dataFim) + 1;

        double progresso;

        if (totalDiasNoPeriodo > 0) {
            // Cálculo do progresso: Registros / Dias no Período
            progresso = (double) totalRegistros / totalDiasNoPeriodo;
        } else {
            progresso = 0.0;
        }

        progresso = Math.round(progresso * 100.0) / 100.0; // Arredonda para 2 casas

        // Retorna o objeto Evolucao com o novo campo
        String metaDetalhada = String.format(
                "Análise do período: %d dias. Meta: %d registros.",
                totalDiasNoPeriodo,
                totalRegistros
        );

        return new Evolucao(metaDetalhada, progresso, totalRegistros);
    }
}