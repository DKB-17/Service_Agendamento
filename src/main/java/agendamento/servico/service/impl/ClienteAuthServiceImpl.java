package agendamento.servico.service.impl;

import agendamento.servico.adapter.ClienteAdapter;
import agendamento.servico.dto.LoginRequest;
import agendamento.servico.dto.RegistroCliente;
import agendamento.servico.entity.Cliente;
import agendamento.servico.repository.ClienteRepository;
import agendamento.servico.service.ClienteAuthService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteAuthServiceImpl implements ClienteAuthService {

    private final ClienteRepository clienteRepository;


    public ClienteAuthServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public RegistroCliente login(LoginRequest loginRequest) {
        Optional<Cliente> clienteOptional = clienteRepository.findByEmailAndPassword(loginRequest.email(), loginRequest.senha());

        if (clienteOptional.isPresent() && clienteOptional.get().getDeletedAt() == null) {
            return ClienteAdapter.fromEntityToRegistroCliente(clienteOptional.get());
        } else {
            throw new RuntimeException("Login ou Senha incorreta");
        }
    }
}
