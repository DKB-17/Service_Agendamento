package agendamento.servico.controller;

import agendamento.servico.dto.AtualizarBarbeiro;
import agendamento.servico.dto.CadastroBarbeiro;
import agendamento.servico.dto.RegistroBarbeiro;
import agendamento.servico.service.BarbeiroService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/barbeiros")
@CrossOrigin
public class BarbeiroController {

    private final BarbeiroService barbeiroService;

    @Autowired
    public BarbeiroController(@Lazy BarbeiroService barbeiroService) {
        this.barbeiroService = barbeiroService;
    }


    @PostMapping()
    public ResponseEntity<RegistroBarbeiro> cadastrarBarbeiro(
            @RequestBody @Valid CadastroBarbeiro dados) {
        try {
            RegistroBarbeiro regisBarbeiro = this.barbeiroService.cadastrarBarbeiro(dados); // Seu service precisa lidar com imagem nula/vazia
            return ResponseEntity.status(HttpStatus.CREATED).body(regisBarbeiro);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping( path = "/imagem/{idBarbeiro}/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void atribuirImagemBarbeiro( @PathVariable Long idBarbeiro, @RequestParam(name = "imagem") MultipartFile arquivo){
        this.barbeiroService.atribuirImagemBarbeiro(arquivo, idBarbeiro);
    }

    @GetMapping(path = "/imagem/{idBarbeiro}")
    public ResponseEntity<String> buscarImagem(@PathVariable Long idBarbeiro){
        byte[] imagem = this.barbeiroService.buscarImagemDoBarbeiro(idBarbeiro);
        String base64 = Base64.getEncoder().encodeToString(imagem);
        return ResponseEntity.ok()
                .body("data:image/png;base64," + base64);
    }

    @GetMapping()
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
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping()

    public ResponseEntity<RegistroBarbeiro> atualizarBarbeiro(@RequestBody @Valid AtualizarBarbeiro dados){

            RegistroBarbeiro barbeiro = this.barbeiroService.atualizarBarbeiro(dados);
            return ResponseEntity.ok(barbeiro);

    }

    @PutMapping("/{id}/desativar")
    @Transactional
    public ResponseEntity<HttpStatus> desativarBarbeiro(@PathVariable Long id){
        try {
            this.barbeiroService.desativarBarbeiro(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}/ativar")
    @Transactional
    public ResponseEntity<RegistroBarbeiro> ativarBarbeiro(@PathVariable Long id){
        try {
            RegistroBarbeiro barbeiro = this.barbeiroService.ativarBarbeiro(id);
            return ResponseEntity.ok(barbeiro);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

}
