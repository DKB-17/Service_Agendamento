package agendamento.servico.controller;

import agendamento.servico.dto.RegistroCaixa;
import agendamento.servico.dto.StatusAgendamentosPorDia;
import agendamento.servico.dto.servico.ServicosVendidos;
import agendamento.servico.service.CaixaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    @GetMapping("/faturamento-total")
    public ResponseEntity<BigDecimal> calculaFaturamentoTotal() {
        return ResponseEntity.status(HttpStatus.OK).body(this.caixaService.faturalmentoTotal());
    }

    @GetMapping("/total-agendamentos")
    public ResponseEntity<Long> quantidadeTotalAgendamentos() {
        return ResponseEntity.ok().body(this.caixaService.totalAgendamentos());
    }

    @GetMapping(path = "/agendamentos-dia/{dataRequerida}")
    public ResponseEntity<StatusAgendamentosPorDia> buscarAgendamentosParaODia(@PathVariable LocalDate dataRequerida){
        return ResponseEntity.ok(this.caixaService.buscarAgendamentosParaODia(dataRequerida));
    }

    @GetMapping(path = "/servico-vendidos")
    public ResponseEntity<List<ServicosVendidos>> buscarServicoesMaisVendidos(){
        return ResponseEntity.ok(this.caixaService.buscarServicoesMaisVendidos());
    }
}
