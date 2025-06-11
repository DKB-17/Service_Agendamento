package agendamento.servico.dto.agenda;

import agendamento.servico.entity.enums.Etapa;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FiltroAgenda(
        Long id,
        String nome,
        String contato,
        LocalDate dia,
        BigDecimal valor,
        Etapa etapa
) {
}
