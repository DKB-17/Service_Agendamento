package agendamento.servico.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record CadastroHorario(
        @NotNull
        LocalTime horarioInicio,
        @NotNull
        LocalTime horarioFim
) { }
