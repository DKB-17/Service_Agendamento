package agendamento.servico.controller;

import agendamento.servico.dto.CadastroAgenda;
import agendamento.servico.dto.RegistroAgenda;
import agendamento.servico.entity.Agenda;
import agendamento.servico.service.AgendaService;
import agendamento.servico.service.BarbeiroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agenda")
@CrossOrigin
public class AgendaController {


    private final AgendaService agendaService;

    @Autowired
    public AgendaController(@Lazy AgendaService agendaService) {
        this.agendaService = agendaService;
    }


    @PostMapping("/cadastrar")
    public ResponseEntity cadastrarAgenda(@RequestBody @Valid CadastroAgenda agenda) {
        try {
            RegistroAgenda registroAgenda = this.agendaService.cadastrarAgenda(agenda);
            return ResponseEntity.status(HttpStatus.OK).body(registroAgenda);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
