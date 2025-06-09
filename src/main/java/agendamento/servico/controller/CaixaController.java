package agendamento.servico.controller;

import agendamento.servico.dto.RegistroCaixa;
import agendamento.servico.service.CaixaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/caixas")
@CrossOrigin
public class CaixaController {

    public final CaixaService caixaService;

    @Autowired
    public CaixaController(@Lazy CaixaService caixaService) {
        this.caixaService = caixaService;
    }

    @GetMapping()
    public ResponseEntity<List<RegistroCaixa>> listarCaixas() {
        List<RegistroCaixa> lstCaixa = caixaService.listarCaixas();
        return ResponseEntity.ok().body(lstCaixa);
    }

}
