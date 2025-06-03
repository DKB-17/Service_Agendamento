package agendamento.servico.controller;

import agendamento.servico.dto.CadastroAgenda;
import agendamento.servico.dto.RegistroAgenda;
import agendamento.servico.service.AgendaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agendamentos")
@CrossOrigin
public class AgendaController {


    private final AgendaService agendaService;

    @Autowired
    public AgendaController(@Lazy AgendaService agendaService) {
        this.agendaService = agendaService;
    }


    @PostMapping()
    public ResponseEntity<RegistroAgenda> cadastrarAgenda(@RequestBody @Valid CadastroAgenda agenda) {
        try {
            RegistroAgenda registroAgenda = this.agendaService.cadastrarAgenda(agenda);
            return ResponseEntity.status(HttpStatus.OK).body(registroAgenda);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
