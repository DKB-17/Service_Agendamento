package agendamento.servico.service;

import agendamento.servico.dto.AtualizarServico;
import agendamento.servico.dto.CadastroServico;
import agendamento.servico.dto.RegistroServico;
import jakarta.validation.Valid;

import java.util.List;

public interface ServicoService {

    RegistroServico cadastrarServico(CadastroServico dados);
    RegistroServico buscarServico(Long id);
    void desativarServico(Long id);
    RegistroServico ativarServico(Long id);
    RegistroServico atualizarServico(AtualizarServico dados);
    List<RegistroServico> listarServicos();

}
