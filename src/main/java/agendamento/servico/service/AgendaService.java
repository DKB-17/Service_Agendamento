package agendamento.servico.service;

import agendamento.servico.dto.*;

import java.util.List;

public interface AgendaService {
    RegistroAgenda cadastrarAgenda(CadastroAgenda agenda);
    List<RegistroAgenda> listarAgendas();
    RegistroAgenda buscarAgenda(Long id);
    RegistroAgenda alterarAgenda(Long id, AtualizarAgenda agenda);
    List<RegistroAgenda> buscarAgendaPorFiltro(FiltroAgenda agenda);
    void desativarAgenda(Long id);
}
