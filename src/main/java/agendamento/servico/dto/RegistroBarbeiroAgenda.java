package agendamento.servico.dto;

import java.time.Instant;
import java.util.Set;

public record RegistroBarbeiroAgenda(
        Long id,
        String nome,
        String caminhoImagem,
        Instant createdAt,
        Instant updatedAd
) {
}
