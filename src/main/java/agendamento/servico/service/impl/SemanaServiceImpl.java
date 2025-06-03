package agendamento.servico.service.impl;

import agendamento.servico.adapter.SemanaAdapter;
import agendamento.servico.dto.RegistroDiasSemana;
import agendamento.servico.repository.DiasDaSemanaRepository;
import agendamento.servico.service.SemanaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SemanaServiceImpl implements SemanaService {

    private DiasDaSemanaRepository diasDaSemanaRepository;

    @Override
    public List<RegistroDiasSemana> listarDiasSemanas() {
        return this.diasDaSemanaRepository.findAll().stream().map(SemanaAdapter::fromEntityToRegistroDiasSemana).toList();
    }
}
