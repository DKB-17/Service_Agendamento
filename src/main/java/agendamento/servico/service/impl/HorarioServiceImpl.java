package agendamento.servico.service.impl;

import agendamento.servico.adapter.HorarioAdapter;
import agendamento.servico.dto.AtualizarHorario;
import agendamento.servico.dto.CadastroHorario;
import agendamento.servico.dto.RegistroHorario;
import agendamento.servico.entity.Horario;
import agendamento.servico.repository.HorarioRepository;
import agendamento.servico.service.HorarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class HorarioServiceImpl implements HorarioService {

    private HorarioRepository horarioRepository;

    @Override
    public RegistroHorario cadastrarHorario(CadastroHorario dados) {
        Horario horario = HorarioAdapter.fromCadastroHorarioToEntity(dados);
        this.horarioRepository.save(horario);
        return HorarioAdapter.fromEntityToRegistroHorario(horario);
    }

    @Override
    public List<RegistroHorario> listarHorarios() {
        return this.horarioRepository.findByDeletedAtIsNull().stream().map(HorarioAdapter::fromEntityToRegistroHorario).toList();
    }

    @Override
    public RegistroHorario buscarHorario(Long id) {
        Horario horario = this.buscarHorarioPorId(id);

        return HorarioAdapter.fromEntityToRegistroHorario(horario);
    }

    @Override
    public RegistroHorario atualizarHorario(AtualizarHorario dados) {
        Horario horario = this.buscarHorarioPorId(dados.id());

        if(dados.horarioInicio().isBefore(dados.horarioFim())){
            horario.setHorarioInicio(dados.horarioInicio());
            horario.setHorarioFim(dados.horarioFim());
            horario.setUpdatedAt(Instant.now());
        }else{
            throw new RuntimeException("Horario invalido");
        }

        return HorarioAdapter.fromEntityToRegistroHorario(this.horarioRepository.save(horario));

    }

    @Override
    public void desativarHorario(Long id) {
        Horario horario = this.buscarHorarioPorId(id);
        horario.setDeletedAt(Instant.now());

        this.horarioRepository.save(horario);
    }

    @Override
    public RegistroHorario ativarHorario(Long id) {
        Horario horario = this.horarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro de horario nao existe"));

        horario.setDeletedAt(null);
        return HorarioAdapter.fromEntityToRegistroHorario(horario);

    }

    private Horario buscarHorarioPorId(Long id) {
        Horario horario = this.horarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro de horario nao existe"));
        if(horario.getDeletedAt() != null){throw new RuntimeException("Registro de horario nao existe");};
        return horario;
    }
}
