package agendamento.servico.adapter;

import agendamento.servico.dto.agenda.RegistroAgenda;
import agendamento.servico.entity.Agenda;

import java.util.List;
import java.util.stream.Collectors;

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
                agenda.getValor(),
                agenda.getEtapa()
        );
    }

    public static List<RegistroAgenda> converter (List<Agenda> agenda){
        return agenda.stream().map(AgendaAdapter::fromEntityToRegistroAgenda).collect(Collectors.toList());
    }
}
