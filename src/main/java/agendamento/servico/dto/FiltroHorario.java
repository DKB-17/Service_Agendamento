package agendamento.servico.dto;

import java.time.Instant;
import java.time.LocalTime;

public record FiltroHorario(
        Long id,
        LocalTime horarioInicio,
        LocalTime horarioFim,
        Instant createdAt
) {
}
