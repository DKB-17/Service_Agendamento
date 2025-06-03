package agendamento.servico.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.Duration;

public record AtualizarServico(
        Long id,
        @NotBlank
        String descricao,

        @NotNull
        BigDecimal valor,

        @NotNull
        Duration duracao
)
{ }
