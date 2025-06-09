package agendamento.servico.service.impl;

import agendamento.servico.adapter.ServicoAdapter;
import agendamento.servico.dto.AtualizarServico;
import agendamento.servico.dto.CadastroServico;
import agendamento.servico.dto.RegistroServico;
import agendamento.servico.entity.Servico;
import agendamento.servico.repository.ServicoRepository;
import agendamento.servico.service.ServicoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServicoServiceImpl implements ServicoService {

    private ServicoRepository servicoRepository;

    @Override
    public RegistroServico cadastrarServico(CadastroServico dados) {
        Servico servico = ServicoAdapter.fromCadastroServicoToEntity(dados);
        this.servicoRepository.save(servico);
        return ServicoAdapter.fromEntityToRegistroServico(servico);
    }

    @Override
    public RegistroServico buscarServico(Long id) {
        Optional<Servico> servico = this.servicoRepository.findById(id);
        if(servico.isEmpty() || servico.get().getDeletedAt() != null) {
            throw new RuntimeException();
        }else{
            return ServicoAdapter.fromEntityToRegistroServico(servico.get());
        }
    }

    @Override
    public RegistroServico ativarServico(Long id) {
        Optional<Servico> servico = this.servicoRepository.findById(id);
        if(servico.isEmpty()) {
            throw new RuntimeException("Registro de servico nao existe");
        } else {
            servico.get().setDeletedAt(null);
            return ServicoAdapter.fromEntityToRegistroServico(servico.get());
        }
    }

    @Override
    public void desativarServico(Long id) {
        Servico servico = this.servicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Erro ao buscar serviço"));

        servico.setDeletedAt(Instant.now());
        this.servicoRepository.save(servico);
    }

    @Override
    public RegistroServico atualizarServico(AtualizarServico dados) {
        Servico servico = this.servicoRepository
                .findById(dados.id())
                .orElseThrow(() -> new RuntimeException("Erro ao buscar serviço"));

        servico.setDescricao(dados.descricao());
        servico.setValor(dados.valor());

        servico.setUpdatedAt(Instant.now());
        return ServicoAdapter.fromEntityToRegistroServico(this.servicoRepository.save(servico));
    }

    @Override
    public List<RegistroServico> listarServicos() {
        return this.servicoRepository
                .findByDeletedAtIsNull()
                .stream()
                .map(ServicoAdapter::fromEntityToRegistroServico)
                .toList();
    }
}
