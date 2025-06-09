package agendamento.servico.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.Duration;

public record AtualizarServico(
        @NotNull
        Long id,
        @NotBlank
        String descricao,
        @NotNull
                @Positive
        BigDecimal valor
        )
{ }
