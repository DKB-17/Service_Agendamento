package agendamento.servico.service;

import agendamento.servico.dto.BuscaPeriodoCaixa;
import agendamento.servico.dto.RegistroCaixa;

import java.util.List;

public interface CaixaService {
    List<RegistroCaixa> listarCaixas();
    RegistroCaixa buscarCaixa(Long id);
    List<RegistroCaixa> buscarCaixaPorPeriodo(BuscaPeriodoCaixa periodo);
    void desativarCaixa(Long id);
    RegistroCaixa ativarCaixa(Long id);

}
