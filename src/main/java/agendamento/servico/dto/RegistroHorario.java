package agendamento.servico.dto;

import java.time.Instant;
import java.time.LocalTime;

public record RegistroHorario(
        Long id,
        LocalTime horarioInicio,
        LocalTime horarioFim,
        Instant deletedAt,
        Instant createdAt,
        Instant updatedAt
) {
}
