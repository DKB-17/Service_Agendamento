package agendamento.servico.dto;

import java.time.Instant;

public record RegistroServico(
        Long id,
        String descricao,
        Instant createAt
) {
}
