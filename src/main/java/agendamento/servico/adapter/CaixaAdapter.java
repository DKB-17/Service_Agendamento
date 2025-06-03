package agendamento.servico.adapter;

import agendamento.servico.dto.CadastroAgenda;
import agendamento.servico.entity.Caixa;

import java.math.BigDecimal;
import java.time.Instant;

public class CaixaAdapter {

    public static Caixa fromCadastroAgendaToEntity(CadastroAgenda dados) {
        return new Caixa(
                null,
                BigDecimal.ZERO,
                dados.dia(),
                null,
                Instant.now(),
                Instant.now()
        );
    }
}
