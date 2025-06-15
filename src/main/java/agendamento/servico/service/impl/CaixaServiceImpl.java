package agendamento.servico.service.impl;

import agendamento.servico.adapter.CaixaAdapter;
import agendamento.servico.dto.BuscaPeriodoCaixa;
import agendamento.servico.dto.RegistroCaixa;
import agendamento.servico.dto.StatusAgendamentosPorDia;
import agendamento.servico.dto.servico.ServicosVendidos;
import agendamento.servico.entity.Agenda;
import agendamento.servico.entity.Caixa;
import agendamento.servico.entity.Servico;
import agendamento.servico.entity.enums.Etapa;
import agendamento.servico.repository.AgendaRepository;
import agendamento.servico.repository.CaixaRepository;
import agendamento.servico.service.CaixaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CaixaServiceImpl implements CaixaService {

    private CaixaRepository caixaRepository;
    private AgendaRepository agendaRepository;

    @Override
    public List<RegistroCaixa> listarCaixas() {
        return this.caixaRepository.findByDeleteAtIsNull().stream().map(CaixaAdapter::fromEntityToRegistroCaixa).toList();
    }

    @Override
    public RegistroCaixa buscarCaixa(Long id) {
        Caixa caixa = buscarCaixaPorId(id);
        return CaixaAdapter.fromEntityToRegistroCaixa(caixa);
    }

    @Override
    public List<RegistroCaixa> buscarCaixaPorPeriodo(BuscaPeriodoCaixa periodo) {
        return this.caixaRepository
                .findByDiaBetween(periodo.periodoInicial(), periodo.periodoFinal())
                .stream()
                .map(CaixaAdapter::fromEntityToRegistroCaixa)
                .toList();
    }

    @Override
    public void desativarCaixa(Long id) {
        Caixa caixa = buscarCaixaPorId(id);
        caixa.setDeleteAt(Instant.now());
        this.caixaRepository.save(caixa);
    }

    @Override
    public RegistroCaixa ativarCaixa(Long id) {

        Caixa caixa = buscarCaixaPorId(id);
        caixa.setDeleteAt(null);
        return CaixaAdapter.fromEntityToRegistroCaixa(this.caixaRepository.save(caixa));

    }

    @Override
    public BigDecimal faturalmentoTotal() {
        List<Caixa> caixas = this.caixaRepository.findAll();
        BigDecimal total = BigDecimal.ZERO;
        for (Caixa caixa : caixas) {
            total = total.add(caixa.getLucro());
        }
        return total;
    }

    @Override
    public StatusAgendamentosPorDia buscarAgendamentosParaODia(LocalDate diaRequerido){
        List<Agenda> agendasParaODia = this.agendaRepository.findAllByDia(diaRequerido);

        Map<Etapa, Long> contagemPorSituacao = agendasParaODia
                .stream()
                .collect(Collectors.groupingBy(Agenda::getEtapa, Collectors.counting()));

        Long agendasPendentes = contagemPorSituacao.getOrDefault(Etapa.PENDENTE, 0L);
        Long agendasConfirmados = contagemPorSituacao.getOrDefault(Etapa.CONFIRMADO, 0L);
        Long agendasCancelados = contagemPorSituacao.getOrDefault(Etapa.CANCELADO, 0L);
        Long agendasConcluidos = contagemPorSituacao.getOrDefault(Etapa.CONCLUIDO, 0L);

        return new StatusAgendamentosPorDia(agendasPendentes, agendasConfirmados, agendasCancelados, agendasConcluidos);
    }

    @Override
    public Long totalAgendamentos (){
        return (long) this.agendaRepository.findAll().size();
    }

    @Override
    public List<ServicosVendidos> buscarServicoesMaisVendidos(){
        List<Agenda> agendasConcluidas = this.agendaRepository.findAllByEtapa(Etapa.CONCLUIDO);
        double totalDeAgendasConcluidas = agendasConcluidas.size();

        if (totalDeAgendasConcluidas == 0) {
            return Collections.emptyList();
        }

        Map<Servico, Long> servicosAgrupados = agendasConcluidas
                .stream()
                .collect(Collectors.groupingBy(Agenda::getServico, Collectors.counting()));

        List<ServicosVendidos> servicosVendidos = new ArrayList<>();

        servicosAgrupados.forEach((servico, quantidade) -> {ServicosVendidos servicoVendido = new ServicosVendidos();
            servicoVendido.setDescricaoDoServico(servico.getDescricao());
            servicoVendido.setQuantidadeDeVendas(quantidade);
            servicoVendido.setValorServico(servico.getValor());

            Double porcentagem = (quantidade / totalDeAgendasConcluidas) * 100.0;
            servicoVendido.setPorcentagemDeVendas(porcentagem);

            servicosVendidos.add(servicoVendido);
        });

        servicosVendidos.sort(Comparator.comparing(ServicosVendidos::getQuantidadeDeVendas).reversed());

        return servicosVendidos;
    }

    private Caixa buscarCaixaPorId(Long id) {
        return this.caixaRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Caixa inexistente"));
    }

    @Override
    public BigDecimal calcularTicketMedio() {
        BigDecimal faturamento = faturalmentoTotal();
        Long totalAgendamentos = totalAgendamentos();

        if (totalAgendamentos == 0) {
            return BigDecimal.ZERO;
        }
        // Divide o faturamento total pelo número total de agendamentos para obter o ticket médio.
        // Utiliza setScale para definir a precisão e RoundingMode para arredondamento.
        return faturamento.divide(new BigDecimal(totalAgendamentos), 2, RoundingMode.HALF_UP);
    }
}
