package agendamento.servico.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;

public record BuscaPeriodoCaixa(
        @NotNull
        LocalDate periodoInicial,
        @NotNull
        LocalDate periodoFinal
) {
}
