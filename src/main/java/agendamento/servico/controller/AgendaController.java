package agendamento.servico.controller;

import agendamento.servico.dto.agenda.AtualizarAgenda;
import agendamento.servico.dto.agenda.CadastroAgenda;
import agendamento.servico.dto.agenda.FiltroAgenda;
import agendamento.servico.dto.agenda.RegistroAgenda;
import agendamento.servico.service.AgendaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendas")
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

    @PostMapping("/filtro")
    public ResponseEntity<List<RegistroAgenda>> buscarAgendaPorFiltro(@RequestBody FiltroAgenda filtro){
        try{
            List<RegistroAgenda> registroAgenda = this.agendaService.buscarAgendaPorFiltro(filtro);
            return ResponseEntity.status(HttpStatus.OK).body(registroAgenda);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<List<RegistroAgenda>> listarAgendas() {
        List<RegistroAgenda> lista = this.agendaService.listarAgendas();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroAgenda> buscarAgendaPorId(@PathVariable Long id) {
        try{
            RegistroAgenda registroAgenda = this.agendaService.buscarAgenda(id);
            return ResponseEntity.status(HttpStatus.OK).body(registroAgenda);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<RegistroAgenda> atualizarAgenda(@PathVariable Long id, @RequestBody @Valid AtualizarAgenda agenda) {
        try{
            RegistroAgenda regiAtualizado = this.agendaService.alterarAgenda(id, agenda);
            return ResponseEntity.status(HttpStatus.OK).body(regiAtualizado);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}/desativar")
    @Transactional
    public ResponseEntity<HttpStatus> desativarAgenda(@PathVariable Long id) {
        try{
            this.agendaService.desativarAgenda(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/proximos-agendamentos-hoje")
    public ResponseEntity<List<RegistroAgenda>> buscarProximosAgendamentosHoje() {
        try {
            List<RegistroAgenda> proximosAgendamentos = this.agendaService.buscarProximosAgendamentosHoje();
            return ResponseEntity.status(HttpStatus.OK).body(proximosAgendamentos);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
