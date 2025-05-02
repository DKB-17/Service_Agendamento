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
import java.util.Optional;

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
        return this.horarioRepository.findByDeleteAtIsNull().stream().map(HorarioAdapter::fromEntityToRegistroHorario).toList();
    }

    @Override
    public RegistroHorario buscarHorario(Long id) {
        Optional<Horario> horario = this.horarioRepository.findById(id);
        if(horario.isEmpty() || horario.get().getDeleteAt() != null){
            throw new RuntimeException();
        }else{
            return HorarioAdapter.fromEntityToRegistroHorario(horario.get());
        }
    }

    @Override
    public RegistroHorario atualizarHorario(AtualizarHorario dados) {
        Optional<Horario> horario = this.horarioRepository.findById(dados.id());
        if (horario.isEmpty() || horario.get().getDeleteAt() != null) {
            throw new RuntimeException("Registro de horario nao existe");
        } else {
            horario.get().atualizaHorario(dados.horarioInicio(), dados.horarioFim());
            horario.get().setUpdateAt(Instant.now());
            return HorarioAdapter.fromEntityToRegistroHorario(this.horarioRepository.save(horario.get()));
        }
    }

    @Override
    public void desativarHorario(Long id) {
        Optional<Horario> horario = this.horarioRepository.findById(id);
        if (horario.isEmpty() || horario.get().getDeleteAt() != null){
            throw new RuntimeException("Registro de horario nao existe");
        } else {
            horario.get().setDeleteAt(Instant.now());
        }
    }

    @Override
    public RegistroHorario ativarHorario(Long id) {
        Optional<Horario> horario = this.horarioRepository.findById(id);
        if (horario.isEmpty()){
            throw new RuntimeException("Registro de horario nao existe");
        } else {
            horario.get().setDeleteAt(null);
            return HorarioAdapter.fromEntityToRegistroHorario(horario.get());
        }
    }
}
