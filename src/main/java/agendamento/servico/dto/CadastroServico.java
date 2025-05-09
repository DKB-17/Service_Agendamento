package agendamento.servico.dto;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.Duration;

public record CadastroServico(
        @NotBlank
        String descricao,
        @NotBlank
        BigDecimal valor,
        @NotBlank
        Duration duracao
) {
}
