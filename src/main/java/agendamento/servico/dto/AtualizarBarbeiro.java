package agendamento.servico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record AtualizarBarbeiro(
        @NotNull
        Long id,

        @NotBlank
        String nome,

        @NotBlank
        String caminhoImagem,
        Set<Long> servicos,
        Set<Long> horarios
) { }
