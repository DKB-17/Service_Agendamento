package agendamento.servico.dto;

import java.time.Instant;

public record RegistroComentario(
        Long id,
        String texto,
        Integer qtd_curtida,
        Long clienteId,
        Long postId,
        Long comentarioId,
        Instant createdAt
) {
}
