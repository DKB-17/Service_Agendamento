package agendamento.servico.dto;

import lombok.NoArgsConstructor;

public record RegistroCurtida(
        Long id,
        Long cliente_id,
        Long post_id,
        Long comentario_id
) {
}
