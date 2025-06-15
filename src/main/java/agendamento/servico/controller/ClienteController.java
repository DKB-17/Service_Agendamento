package agendamento.servico.controller;

import agendamento.servico.dto.CadastroCliente;
import agendamento.servico.dto.RegistroCliente;
import agendamento.servico.entity.Cliente;
import agendamento.servico.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
@CrossOrigin
public class ClienteController {

    public final ClienteService clienteService;

    @Autowired
    public ClienteController(@Lazy ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping()
    public ResponseEntity<RegistroCliente> cadastrarCliente(@RequestBody @Valid CadastroCliente cliente) {
        try {
            RegistroCliente registroCliente = this.clienteService.cadastrarCliente(cliente);
            return ResponseEntity.status(HttpStatus.OK).body(registroCliente);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
