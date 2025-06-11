package agendamento.servico.dto.agenda;

import agendamento.servico.dto.RegistroBarbeiroAgenda;
import agendamento.servico.dto.RegistroHorario;
import agendamento.servico.dto.RegistroServico;
import agendamento.servico.entity.enums.Etapa;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RegistroAgenda(
        Long id,
        String nome,
        String contato,
        RegistroHorario registroHorario,
        RegistroServico registroServico,
        RegistroBarbeiroAgenda registroBarbeiro,
        LocalDate dia,
        BigDecimal valor,
        Etapa etapa
) {
}
