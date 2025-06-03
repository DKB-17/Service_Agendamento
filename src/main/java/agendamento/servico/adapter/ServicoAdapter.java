package agendamento.servico.adapter;

import agendamento.servico.dto.CadastroServico;
import agendamento.servico.dto.RegistroServico;
import agendamento.servico.entity.Servico;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;

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
}
