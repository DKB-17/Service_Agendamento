package agendamento.servico.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record FiltroServico(
        Long id,
        String descricao,
        BigDecimal valor,
        Instant createdAt
) {
}
