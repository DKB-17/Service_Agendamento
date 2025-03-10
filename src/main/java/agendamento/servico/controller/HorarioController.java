package agendamento.servico.controller;

import agendamento.servico.dto.AtualizarHorario;
import agendamento.servico.dto.CadastroHorario;
import agendamento.servico.dto.RegistroHorario;
import agendamento.servico.service.HorarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/horarios")
public class HorarioController {

    @Autowired
    private HorarioService horarioService;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<RegistroHorario> cadastrarHorario(@RequestBody @Valid CadastroHorario dados){
        try{
            if(dados.horarioInicio().isBefore(dados.horarioFim())){
                RegistroHorario regisHorario = this.horarioService.cadastrarHorario(dados);
                return ResponseEntity.status(HttpStatus.CREATED).body(regisHorario);
            }else {
                throw new RuntimeException("Horario invalidos");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/listar")
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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/editar")
    @Transactional
    public ResponseEntity<RegistroHorario> atualizarHorario(@RequestBody @Valid AtualizarHorario dados){
        try{
            RegistroHorario horario = this.horarioService.atualizarHorario(dados);
            return ResponseEntity.ok(horario);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("{id}/desativar")
    @Transactional
    public ResponseEntity<HttpStatus> desativarHorario(@PathVariable Long id){
        try{
            this.horarioService.desativarHorario(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("{id}/ativar")
    @Transactional
    public ResponseEntity<RegistroHorario> ativarHorario(@PathVariable Long id){
        try{
            RegistroHorario horario = this.horarioService.ativarHorario(id);
            return ResponseEntity.ok(horario);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
