package agendamento.servico.controller;

import agendamento.servico.dto.AtualizarHorario;
import agendamento.servico.dto.CadastroHorario;
import agendamento.servico.dto.RegistroDiasSemana;
import agendamento.servico.dto.RegistroHorario;
import agendamento.servico.service.HorarioService;
import agendamento.servico.service.SemanaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/horarios")
@CrossOrigin
public class HorarioController {

    private final HorarioService horarioService;
    private final SemanaService semanaService;

    @Autowired
    public HorarioController(@Lazy HorarioService horarioService, @Lazy SemanaService semanaService) {
        this.horarioService = horarioService;
        this.semanaService = semanaService;
    }

    @GetMapping("/dias_da_semana")
    public ResponseEntity<List<RegistroDiasSemana>> listarDiasDaSemana(){
        List<RegistroDiasSemana> listaDeDiasDaSemana = this.semanaService.listarDiasSemanas();
        return ResponseEntity.ok(listaDeDiasDaSemana);
    }

    @PostMapping()
    @Transactional
    public ResponseEntity<RegistroHorario> cadastrarHorario(@RequestBody @Valid CadastroHorario dados){
        try{
            if(dados.horaInicio().isBefore(dados.horaFim())){
                RegistroHorario regisHorario = this.horarioService.cadastrarHorario(dados);
                return ResponseEntity.status(HttpStatus.CREATED).body(regisHorario);
            }else {
                throw new RuntimeException("Horario invalidos");
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<List<RegistroHorario>> listarHorarios(){
        List<RegistroHorario> listaHorarios = this.horarioService.listarHorarios();
        return ResponseEntity.ok(listaHorarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroHorario> buscarHorario(@PathVariable Long id){
        try {
            RegistroHorario regisHorario = this.horarioService.buscarHorario(id);
            return ResponseEntity.ok(regisHorario);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping()
    @Transactional
    public ResponseEntity<RegistroHorario> atualizarHorario(@RequestBody @Valid AtualizarHorario dados){
        try{
            RegistroHorario horario = this.horarioService.atualizarHorario(dados);
            return ResponseEntity.ok(horario);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("{id}/desativar")
    @Transactional
    public ResponseEntity<HttpStatus> desativarHorario(@PathVariable Long id){
        try{
            this.horarioService.desativarHorario(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("{id}/ativar")
    @Transactional
    public ResponseEntity<RegistroHorario> ativarHorario(@PathVariable Long id){
        try{
            RegistroHorario horario = this.horarioService.ativarHorario(id);
            return ResponseEntity.ok(horario);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

}
