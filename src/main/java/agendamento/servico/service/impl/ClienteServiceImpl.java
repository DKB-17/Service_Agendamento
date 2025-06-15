package agendamento.servico.service.impl;

import agendamento.servico.adapter.AgendaAdapter;
import agendamento.servico.adapter.ClienteAdapter;
import agendamento.servico.adapter.UsuarioAdapter;
import agendamento.servico.dto.CadastroCliente;
import agendamento.servico.dto.RegistroCliente;
import agendamento.servico.entity.Cliente;
import agendamento.servico.entity.Usuario;
import agendamento.servico.repository.ClienteRepository;
import agendamento.servico.repository.UsuarioRepository;
import agendamento.servico.service.ClienteService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final UsuarioRepository usuarioRepository;


    public ClienteServiceImpl(ClienteRepository clienteRepository, UsuarioRepository usuarioRepository) {
        this.clienteRepository = clienteRepository;
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public RegistroCliente cadastrarCliente(CadastroCliente dados) {

        Usuario usuario = buscarUsuarioPorContato(dados);
        this.usuarioRepository.save(usuario);
        
        Cliente cliente = new Cliente(
                null,
                null,
                usuario,
                dados.CPF(),
                dados.email(),
                dados.password(),
                null,
                Instant.now(),
                Instant.now()
        );
        return ClienteAdapter.fromEntityToRegistroCliente(this.clienteRepository.save(cliente));

    }

    private Usuario buscarUsuarioPorContato(CadastroCliente dados) {
        return this.usuarioRepository.findByContato(dados.contato()).orElse(this.usuarioRepository.save(UsuarioAdapter.fromCadastroClienteToEntity(dados)));
    }
}
