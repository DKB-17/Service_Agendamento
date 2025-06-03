package agendamento.servico.adapter;

import agendamento.servico.dto.RegistroDiasSemana;
import agendamento.servico.entity.DiaDaSemana;
import agendamento.servico.service.SemanaService;

public class SemanaAdapter {

    public static RegistroDiasSemana fromEntityToRegistroDiasSemana(DiaDaSemana diaDaSemana) {
        return new RegistroDiasSemana(
                diaDaSemana.getId(),
                diaDaSemana.getDescricao()
        );
    }
}
