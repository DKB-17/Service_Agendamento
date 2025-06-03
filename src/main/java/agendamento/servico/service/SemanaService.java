package agendamento.servico.service;

import agendamento.servico.dto.RegistroDiasSemana;

import java.util.List;

public interface SemanaService {
    List<RegistroDiasSemana> listarDiasSemanas();
}
