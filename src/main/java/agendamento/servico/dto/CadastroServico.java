package agendamento.servico.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

public record CadastroServico(
        @NotBlank
        String descricao,
        @NotNull
                @Positive
        BigDecimal valor
) {
}
