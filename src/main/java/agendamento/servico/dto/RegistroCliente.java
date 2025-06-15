package agendamento.servico.dto;

import agendamento.servico.entity.Imagem;

import java.time.Instant;

public record RegistroCliente(
        Long id,
        Imagem imagem,
        RegistroUsuario usuario,
        Instant createdAt,
        Instant updatedAt
) {
}
