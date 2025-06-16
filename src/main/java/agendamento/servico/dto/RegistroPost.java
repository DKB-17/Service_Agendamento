package agendamento.servico.dto;

import java.time.Instant;
import java.util.List;

public record RegistroPost(
        Long id,
        RegistroCliente cliente,
        String legenda,
        Integer avaliacao,
        Integer qtd_curtidas,
        List<RegistroCurtida> curtidas,
        Instant createdAt,
        Instant updatedAt
) {
}
