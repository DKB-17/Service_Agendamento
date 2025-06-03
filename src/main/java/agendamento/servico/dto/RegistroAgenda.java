package agendamento.servico.dto;

import java.math.BigDecimal;
import java.util.Date;

public record RegistroAgenda(
        Long id,
        String nome,
        String contato,
        RegistroHorario registroHorario,
        RegistroServico registroServico,
        RegistroBarbeiroAgenda registroBarbeiro,
        Date dia,
        BigDecimal valor
) {
}
