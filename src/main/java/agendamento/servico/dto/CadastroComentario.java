package agendamento.servico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroComentario(
        @NotBlank
        String texto,
        @NotNull
        Long cliente_id,
        Long post_id,
        Long comentario_id
) {
}
