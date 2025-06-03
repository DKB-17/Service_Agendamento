package agendamento.servico.service;

import agendamento.servico.dto.AtualizarEtapa;
import agendamento.servico.dto.CadastroEtapa;
import agendamento.servico.dto.RegistroEtapa;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EtapaService {

    RegistroEtapa cadastrarEtapa(CadastroEtapa cadastrado);
    List<RegistroEtapa> listarEtapas();
    RegistroEtapa buscarEtapa(Long id);
    RegistroEtapa atualizarEtapa(AtualizarEtapa atualizado);
    void desativarEtapa(Long id);
    RegistroEtapa ativarEtapa(Long id);


}
