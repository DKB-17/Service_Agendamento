package agendamento.servico.dto;

import jakarta.validation.constraints.NotBlank;

public record CadastroServico(
        @NotBlank
        String descricao
) {
}
