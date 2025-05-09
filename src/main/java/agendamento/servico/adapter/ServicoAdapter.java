package agendamento.servico.adapter;

import agendamento.servico.dto.CadastroServico;
import agendamento.servico.dto.RegistroServico;
import agendamento.servico.entity.Servico;

import java.time.Instant;

public class ServicoAdapter {
    public static Servico fromCadastroServicoToEntity(CadastroServico cadastroServico) {
        return new Servico(
            null,
            cadastroServico.descricao(),
            cadastroServico.valor(),
            cadastroServico.duracao(),
            null,
            Instant.now(),
            Instant.now()
        );
    }
    public static RegistroServico fromEntityToRegistroServico(Servico servico) {
        return new RegistroServico(
            servico.getId(),
            servico.getDescricao(),
            servico.getCreateAt()
        );
    }
}
