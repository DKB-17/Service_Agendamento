package agendamento.servico.dto;

import jakarta.validation.constraints.NotBlank;

public record CadastroEtapa(
        @NotBlank
        String sigla,
        @NotBlank
        String descricao
) {
}
