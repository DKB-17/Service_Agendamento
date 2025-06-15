package agendamento.servico.adapter;

import agendamento.servico.dto.RegistroCliente;
import agendamento.servico.entity.Cliente;

public class ClienteAdapter {

    public static RegistroCliente fromEntityToRegistroCliente(Cliente cliente) {
        return new RegistroCliente(
                cliente.getId(),
                cliente.getImagem(),
                UsuarioAdapter.fromEntityToRegistroUsuario(cliente.getUsuario()),
                cliente.getCreatedAt(),
                cliente.getUpdatedAt()
        );
    }
}
