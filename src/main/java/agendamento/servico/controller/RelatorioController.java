package agendamento.servico.controller;

import agendamento.servico.repository.CaixaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/relatorio")
@CrossOrigin
public class RelatorioController {

    private final CaixaRepository caixaRepository;

    @Autowired
    public RelatorioController(CaixaRepository caixaRepository) {
        this.caixaRepository = caixaRepository;
    }

    @GetMapping()
    public BigDecimal RelatorioLucroMes(){
        LocalDate d1 = LocalDate.of(2025, 6, 1);
        LocalDate d2 = LocalDate.of(2025, 6, 30);
        return this.caixaRepository.LucroDoMes(d1, d2);
    }

}
