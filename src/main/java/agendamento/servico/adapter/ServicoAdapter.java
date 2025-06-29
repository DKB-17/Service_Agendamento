package agendamento.servico.adapter;

import agendamento.servico.dto.CadastroServico;
import agendamento.servico.dto.RegistroServico;
import agendamento.servico.entity.Servico;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ServicoAdapter {
    public static Servico fromCadastroServicoToEntity(CadastroServico cadastroServico) {
        return new Servico(
            null,
            cadastroServico.descricao(),
            cadastroServico.valor(),
            Collections.emptyList(),
            null,
            Instant.now(),
            Instant.now()
        );
    }
    public static RegistroServico fromEntityToRegistroServico(Servico servico) {
        return new RegistroServico(
                servico.getId(),
                servico.getDescricao(),
                servico.getValor(),
                servico.getCreatedAt(),
                servico.getUpdatedAt()
        );
    }

    public static List<RegistroServico> converter(List<Servico> servicos) {
        return servicos.stream().map(ServicoAdapter::fromEntityToRegistroServico).collect(Collectors.toList());
    }
}
