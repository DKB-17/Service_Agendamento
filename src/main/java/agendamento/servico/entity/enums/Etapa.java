package agendamento.servico.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Etapa {
        PENDENTE("PENDENTE"),
        CONFIRMADO("CONFIRMADO"),
        CANCELADO("CANCELADO"),
        CONCLUIDO("CONCLUIDO");

        private final String valor;
}
