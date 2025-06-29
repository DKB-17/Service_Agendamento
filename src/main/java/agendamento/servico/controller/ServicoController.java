package agendamento.servico.controller;

import agendamento.servico.dto.AtualizarServico;
import agendamento.servico.dto.CadastroServico;
import agendamento.servico.dto.FiltroServico;
import agendamento.servico.dto.RegistroServico;
import agendamento.servico.service.ServicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicos")
@CrossOrigin
public class ServicoController {

    private final ServicoService servicoService;

    @Autowired
    public ServicoController(@Lazy ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @PostMapping()
    @Transactional
    public ResponseEntity<RegistroServico> cadastrarServico(@RequestBody @Valid CadastroServico dados) {
        try{

            RegistroServico registroServico = this.servicoService.cadastrarServico(dados);
            return ResponseEntity.status(HttpStatus.CREATED).body(registroServico);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/filtro")
    public ResponseEntity<List<RegistroServico>> buscarServicoPorFiltro (@RequestBody FiltroServico filtro){
        try{
            List<RegistroServico> servicos = this.servicoService.buscarServicoPorFiltro(filtro);
            return ResponseEntity.ok(servicos);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<RegistroServico>> listarServicos() {
        List<RegistroServico> lista = this.servicoService.listarServicos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroServico> buscarServico(@PathVariable Long id) {
        try{
            RegistroServico servico = this.servicoService.buscarServico(id);
            return ResponseEntity.ok(servico);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping()
    public ResponseEntity<RegistroServico> editarServico(@RequestBody @Valid AtualizarServico dados) {
        try{
            RegistroServico servico = this.servicoService.atualizarServico(dados);
            return ResponseEntity.ok(servico);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("{id}/desativar")
    public ResponseEntity<HttpStatus> desativarServico(@PathVariable Long id) {
        try{
            this.servicoService.desativarServico(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("{id}/ativar")
    public ResponseEntity<RegistroServico> ativarServico(@PathVariable Long id) {
        try {
            RegistroServico servico = this.servicoService.ativarServico(id);
            return ResponseEntity.ok(servico);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
