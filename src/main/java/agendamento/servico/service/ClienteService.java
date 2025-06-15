package agendamento.servico.service;

import agendamento.servico.dto.CadastroCliente;
import agendamento.servico.dto.RegistroCliente;

public interface ClienteService {
    RegistroCliente cadastrarCliente(CadastroCliente dados);
}
