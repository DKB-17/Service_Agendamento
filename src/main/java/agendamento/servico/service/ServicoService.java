package agendamento.servico.service;

import agendamento.servico.dto.AtualizarServico;
import agendamento.servico.dto.CadastroServico;
import agendamento.servico.dto.RegistroServico;

import java.util.List;

public interface ServicoService {

    public RegistroServico cadastrarServico(CadastroServico dados);
    public RegistroServico buscarServico(Long id);
    public void desativarServico(Long id);
    public RegistroServico ativarServico(Long id);
    public RegistroServico atualizarServico(Long id, AtualizarServico dados);
    public List<RegistroServico> listarServicos();

}
