package agendamento.servico.adapter;

import agendamento.servico.dto.RegistroAgenda;
import agendamento.servico.entity.Agenda;

public class AgendaAdapter {
    public static RegistroAgenda fromEntityToRegistroAgenda(Agenda agenda){
        return new RegistroAgenda(
                agenda.getId(),
                agenda.getUsuario().getNome(),
                agenda.getUsuario().getContato(),
                HorarioAdapter.fromEntityToRegistroHorario(agenda.getHorario()),
                ServicoAdapter.fromEntityToRegistroServico(agenda.getServico()),
                BarbeiroAdapter.fromEntityToRegistroBarbeiroAgenda(agenda.getBarbeiro()),
                agenda.getDia(),
                agenda.getValor()
        );
    }
}
