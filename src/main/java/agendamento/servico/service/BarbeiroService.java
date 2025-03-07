package agendamento.servico.service;

import agendamento.servico.dto.AtualizarBarbeiro;
import agendamento.servico.dto.CadastroBarbeiro;
import agendamento.servico.dto.RegistroBarbeiro;

import java.util.List;

public interface BarbeiroService {

    public RegistroBarbeiro cadastrarBarbeiro(CadastroBarbeiro dados);
    public List<RegistroBarbeiro> listarBarbeiros();
    public RegistroBarbeiro buscarBarbeiro(Long id);
    public RegistroBarbeiro atualizarBarbeiro(AtualizarBarbeiro dados);
    public void desativarBarbeiro(Long id);
    public RegistroBarbeiro ativarBarbeiro(Long id);
}
