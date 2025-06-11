package agendamento.servico.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusAgendamentosPorDia {
    private Long agendasPendentes;
    private Long agendasConfirmados;
    private Long agendasCancelados;
    private Long agendasConcluidos;
}
