package agendamento.servico.service.impl;

import agendamento.servico.adapter.CaixaAdapter;
import agendamento.servico.dto.BuscaPeriodoCaixa;
import agendamento.servico.dto.RegistroCaixa;
import agendamento.servico.entity.Caixa;
import agendamento.servico.repository.CaixaRepository;
import agendamento.servico.service.CaixaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class CaixaServiceImpl implements CaixaService {

    private CaixaRepository caixaRepository;

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

    private Caixa buscarCaixaPorId(Long id) {
        return this.caixaRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Caixa inexistente"));
    }

}
