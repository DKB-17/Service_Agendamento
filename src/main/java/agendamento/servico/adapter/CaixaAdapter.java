package agendamento.servico.adapter;

import agendamento.servico.dto.CadastroAgenda;
import agendamento.servico.dto.RegistroCaixa;
import agendamento.servico.entity.Caixa;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;

public class CaixaAdapter {

    public static Caixa fromCadastroAgendaToEntity(CadastroAgenda dados) {
        return new Caixa(
                null,
                BigDecimal.ZERO,
                dados.dia(),
                new ArrayList<>(),
                null,
                Instant.now(),
                Instant.now()
        );
    }
    public static RegistroCaixa fromEntityToRegistroCaixa(Caixa caixa) {
        return new RegistroCaixa(
                caixa.getId(),
                caixa.getLucro(),
                caixa.getDia()
        );
    }
}
