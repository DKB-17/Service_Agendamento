package agendamento.servico.dto;

import java.sql.Blob;

public record CadastroPost(
        Long cliente_id,
        String legenda,
        Integer avaliacao
) {
}
