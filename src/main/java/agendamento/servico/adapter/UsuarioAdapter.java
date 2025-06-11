package agendamento.servico.adapter;

import agendamento.servico.dto.agenda.CadastroAgenda;
import agendamento.servico.entity.Usuario;

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
