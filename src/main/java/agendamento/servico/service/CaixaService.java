package agendamento.servico.service;

import agendamento.servico.dto.BuscaPeriodoCaixa;
import agendamento.servico.dto.RegistroCaixa;
import agendamento.servico.dto.StatusAgendamentosPorDia;
import agendamento.servico.dto.servico.ServicosVendidos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface CaixaService {
    List<RegistroCaixa> listarCaixas();
    RegistroCaixa buscarCaixa(Long id);
    List<RegistroCaixa> buscarCaixaPorPeriodo(BuscaPeriodoCaixa periodo);
    void desativarCaixa(Long id);
    RegistroCaixa ativarCaixa(Long id);
    BigDecimal faturalmentoTotal();

    StatusAgendamentosPorDia buscarAgendamentosParaODia(LocalDate diaRequerido);

    List<ServicosVendidos> buscarServicoesMaisVendidos();

    Long totalAgendamentos();

    BigDecimal calcularTicketMedio();
}
