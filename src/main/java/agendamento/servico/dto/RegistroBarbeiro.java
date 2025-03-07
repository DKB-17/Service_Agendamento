package agendamento.servico.dto;

import agendamento.servico.entity.Barbeiro;

import java.time.Instant;

public record RegistroBarbeiro(
        Long id,
        String nome,
        String caminhoImagem,
        Instant createAt
) {
}
