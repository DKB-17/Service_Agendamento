package agendamento.servico.service;

import agendamento.servico.dto.CadastroAgenda;
import agendamento.servico.dto.RegistroAgenda;

import java.util.List;

public interface AgendaService {
    RegistroAgenda cadastrarAgenda(CadastroAgenda agenda);
    List<RegistroAgenda> listarAgendas();
    RegistroAgenda buscarAgenda(Long id);
    RegistroAgenda atualizarAgenda(RegistroAgenda agenda);
    void excluirAgenda(Long id);
}
