package agendamento.servico.dto;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

public record RegistroServico(
        Long id,
        String descricao,
        BigDecimal valor,
        Instant createdAt,
        Instant updatedAt
) {
}
