package agendamento.servico.dto;

import jakarta.persistence.PostUpdate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public record CadastroAgenda(
        @NotBlank
                @NotNull
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
