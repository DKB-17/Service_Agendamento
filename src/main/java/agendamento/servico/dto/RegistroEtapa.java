package agendamento.servico.dto;

import java.time.Instant;

public record RegistroEtapa(
        Long id,
        String sigla,
        String descricao,
        Instant createdAt,
        Instant updatedAt
) {
}
