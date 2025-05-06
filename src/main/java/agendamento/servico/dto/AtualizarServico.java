package agendamento.servico.dto;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Duration;

public record AtualizarServico(
    String descricao,
    BigDecimal valor,
    Duration duracao
)
{ }
