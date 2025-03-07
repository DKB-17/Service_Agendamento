package agendamento.servico.controller;

import agendamento.servico.dto.AtualizarBarbeiro;
import agendamento.servico.dto.CadastroBarbeiro;
import agendamento.servico.dto.RegistroBarbeiro;
import agendamento.servico.service.BarbeiroService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/barbeiros")
@AllArgsConstructor
public class BarbeiroController {

    @Autowired
    private final BarbeiroService barbeiroService;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<RegistroBarbeiro> cadastrarBarbeiro(@RequestBody @Valid CadastroBarbeiro dados){
        RegistroBarbeiro regisBarbeiro = this.barbeiroService.cadastrarBarbeiro(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(regisBarbeiro);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<RegistroBarbeiro>> listarBarbeiros(){
        List<RegistroBarbeiro> lista =  this.barbeiroService.listarBarbeiros();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroBarbeiro> buscarBarbeiro(@PathVariable Long id){
        try{
            RegistroBarbeiro barbeiro = this.barbeiroService.buscarBarbeiro(id);
            return ResponseEntity.ok(barbeiro);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/editar")
    @Transactional
    public ResponseEntity<RegistroBarbeiro> atualizarBarbeiro(@RequestBody @Valid AtualizarBarbeiro dados){
        try {
            RegistroBarbeiro barbeiro = this.barbeiroService.atualizarBarbeiro(dados);
            return ResponseEntity.ok(barbeiro);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}/desativar")
    @Transactional
    public ResponseEntity<HttpStatus> desativarBarbeiro(@PathVariable Long id){
        try {
            this.barbeiroService.desativarBarbeiro(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}/ativar")
    @Transactional
    public ResponseEntity<RegistroBarbeiro> ativarBarbeiro(@PathVariable Long id){
        try {
            RegistroBarbeiro barbeiro = this.barbeiroService.ativarBarbeiro(id);
            return ResponseEntity.ok(barbeiro);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
