package agendamento.servico.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record AtualizarHorario(
        @NotNull
        Long id,
        LocalTime horarioInicio,
        LocalTime horarioFim
) {
}
