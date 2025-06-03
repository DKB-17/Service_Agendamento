package agendamento.servico.adapter;

import agendamento.servico.dto.CadastroAgenda;
import agendamento.servico.entity.Usuario;
import jakarta.validation.constraints.Null;

import java.time.Instant;

public class UsuarioAdapter {
    public static Usuario fromCadastroAgendaToEntity(CadastroAgenda dados) {
        return new Usuario(
                null,
                dados.nome(),
                dados.contato(),
                null,
                Instant.now(),
                Instant.now()
        );
    }
}
