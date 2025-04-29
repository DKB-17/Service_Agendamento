package agendamento.servico.dto;

import jakarta.validation.constraints.NotBlank;

public record CadastroBarbeiro(
        @NotBlank
        String nome,
        @NotBlank
        String caminhoImagem
) { }
