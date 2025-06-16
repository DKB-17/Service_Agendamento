package agendamento.servico.service;

import agendamento.servico.dto.LoginRequest;
import agendamento.servico.dto.RegistroCliente;

public interface ClienteAuthService {
    RegistroCliente login(LoginRequest loginRequest);
}
