package agendamento.servico.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record AtualizarBarbeiro(
        @NotNull
        Long id,
        String nome,
        String caminhoImagem,
        Set<Long> servicos,
        Set<Long> horarios
) { }
