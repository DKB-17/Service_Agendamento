package agendamento.servico.service.impl;

import agendamento.servico.adapter.ServicoAdapter;
import agendamento.servico.dto.AtualizarServico;
import agendamento.servico.dto.CadastroServico;
import agendamento.servico.dto.RegistroServico;
import agendamento.servico.entity.Servico;
import agendamento.servico.repository.ServicoRepository;
import agendamento.servico.service.ServicoService;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public class ServicoServiceImpl implements ServicoService {

    private ServicoRepository servicoRepository;

    @Override
    public RegistroServico cadastrarServico(CadastroServico dados) {
        Servico servico = ServicoAdapter.fromCadastroServicoToEntity(dados);
        servicoRepository.save(servico);
        return ServicoAdapter.fromEntityToRegistroServico(servico);
    }

    @Override
    public RegistroServico buscarServico(Long id) {
        Optional<Servico> servico = servicoRepository.findById(id);
        if(servico.isEmpty() || servico.get().getDeleteAt() != null) {
            throw new RuntimeException();
        }else{
            return ServicoAdapter.fromEntityToRegistroServico(servico.get());
        }
    }

    @Override
    public void desativarServico(Long id) {
        Optional<Servico> servico = servicoRepository.findById(id);
        if(servico.isEmpty() || servico.get().getDeleteAt() != null) {
            throw new RuntimeException("Registro de servico nao existe");
        } else {
            servico.get().setDeleteAt(Instant.now());
        }
    }

    @Override
    public RegistroServico ativarServico(Long id) {
        Optional<Servico> servico = servicoRepository.findById(id);
        if(servico.isEmpty()) {
            throw new RuntimeException("Registro de servico nao existe");
        } else {
            servico.get().setDeleteAt(null);
            return ServicoAdapter.fromEntityToRegistroServico(servico.get());
        }
    }

    @Override
    public RegistroServico atualizarServico(Long id, AtualizarServico dados) {
        Optional<Servico> servico = servicoRepository.findById(id);
        if(servico.isEmpty() || servico.get().getDeleteAt() != null) {
            throw new RuntimeException("Registro de servico nao existe");
        }
        if(dados.descricao() != null){
            servico.get().setDescricao(dados.descricao());
        }
        if(dados.valor() != null){
            servico.get().setValor(dados.valor());
        }
        if (dados.duracao() != null){
            servico.get().setDuracao(dados.duracao());
        }
        servico.get().setUpdateAt(Instant.now());
        return ServicoAdapter.fromEntityToRegistroServico(servico.get());
    }

    @Override
    public List<RegistroServico> listarServicos() {
        return this.servicoRepository.findByDeleteAtIsNull().stream().map(ServicoAdapter::fromEntityToRegistroServico).toList();
    }
}
