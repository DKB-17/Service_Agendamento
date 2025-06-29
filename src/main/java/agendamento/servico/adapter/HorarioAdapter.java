package agendamento.servico.adapter;

import agendamento.servico.dto.CadastroHorario;
import agendamento.servico.dto.RegistroHorario;
import agendamento.servico.entity.Horario;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HorarioAdapter {
    public static Horario fromCadastroHorarioToEntity(CadastroHorario dados){
        return new Horario(
                null,
                dados.horaInicio(),
                dados.horaFim(),
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
                dados.getDeletedAt(),
                dados.getCreatedAt(),
                dados.getUpdatedAt()
        );
    }
    public static List<RegistroHorario> converter (List<Horario> horarios) {
        return horarios.stream().map(HorarioAdapter::fromEntityToRegistroHorario).collect(Collectors.toList());
    }
}
