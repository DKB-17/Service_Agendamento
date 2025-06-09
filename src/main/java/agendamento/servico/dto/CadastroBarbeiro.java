package agendamento.servico.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record CadastroBarbeiro(
        @NotBlank
        String nome,

        Set<Long> servicos,

        Set<Long> horarios
) { }
