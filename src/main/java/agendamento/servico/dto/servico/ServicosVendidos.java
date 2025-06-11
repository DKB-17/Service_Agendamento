package agendamento.servico.dto.servico;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServicosVendidos {
    private String descricaoDoServico;
    private BigDecimal valorServico;
    private Double porcentagemDeVendas;
    private Long quantidadeDeVendas;
}
