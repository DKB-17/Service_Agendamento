package agendamento.servico.controller;

import agendamento.servico.service.BarbeiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agenda")
public class AgendaController {

    /*
    private static AgendaService agendaService;

    @Autowired
    public AgendaController(@Lazy AgendaService agendaService) {
        this.agendaService = agendaService;
    }
    */

    @GetMapping()
    public ResponseEntity teste(){
        return ResponseEntity.status(HttpStatus.OK).body(
                "Agenda Service"
        );
    }

}
