package agendamento.servico.adapter;

import agendamento.servico.dto.CadastroHorario;
import agendamento.servico.dto.RegistroHorario;
import agendamento.servico.entity.Horario;

import java.time.Instant;
import java.util.Collections;

public class HorarioAdapter {
    public static Horario fromCadastroHorarioToEntity(CadastroHorario dados){
        return new Horario(
                null,
                dados.horarioInicio(),
                dados.horarioFim(),
                Collections.emptyList(),
                null,
                Instant.now(),
                Instant.now()
        );
    }
    public static RegistroHorario fromEntityToRegistroHorario(Horario dados){
        return new RegistroHorario(
                dados.getId(),
                dados.getHorarioInicio(),
                dados.getHorarioFim(),
                dados.getCreatedAt(),
                dados.getUpdatedAt()
        );
    }
}
