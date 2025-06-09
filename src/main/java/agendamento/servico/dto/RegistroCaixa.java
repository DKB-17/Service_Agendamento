package agendamento.servico.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public record RegistroCaixa(
    Long id,
    BigDecimal lucro,
    LocalDate dia
) {
}
