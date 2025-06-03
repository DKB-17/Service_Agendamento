package agendamento.servico.dto;

import agendamento.servico.entity.Barbeiro;

import java.time.Instant;
import java.util.Set;

public record RegistroBarbeiro(
        Long id,
        String nome,
        String caminhoImagem,
        Set<RegistroServico> servicos,
        Set<RegistroHorario> horarios,
        Instant createdAt,
        Instant updatedAt
) { }
