package agendamento.servico.controller;

import agendamento.servico.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
@CrossOrigin
public class ClienteController {

    public final ClienteService clienteService;

    @Autowired
    public ClienteController(@Lazy ClienteService clienteService) {
        this.clienteService = clienteService;
    }



}
