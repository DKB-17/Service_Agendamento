package agendamento.servico.dto;

import jakarta.validation.constraints.*;

import java.util.Date;

public record CadastroAgenda(
        @NotBlank
        String nome,

        @NotBlank
        @Pattern(regexp= "\\(\\d{2}\\)\\d{5}-\\d{4}")
        String contato,
        @NotNull
                @Positive
        Long horarioId,
        @NotNull
                @Positive
        Long barbeiroId,
        @NotNull
                @Positive
        Long servicoId,
        @NotNull
                @FutureOrPresent
        Date dia
) { }
