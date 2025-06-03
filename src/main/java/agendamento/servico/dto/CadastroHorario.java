package agendamento.servico.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;
import java.util.Set;

public record CadastroHorario(
        @NotNull
        LocalTime horarioInicio,
        @NotNull
        LocalTime horarioFim,
        @NotEmpty
        Set<Long>diasSemana
) { }
