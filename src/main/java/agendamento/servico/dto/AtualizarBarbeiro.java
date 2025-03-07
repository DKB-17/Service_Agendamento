package agendamento.servico.dto;

import jakarta.validation.constraints.NotNull;

public record AtualizarBarbeiro(
        @NotNull
        Long id,
        String nome,
        String caminhoImagem
) {
}
