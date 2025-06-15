package agendamento.servico.service.impl;

import agendamento.servico.adapter.HorarioAdapter;
import agendamento.servico.dto.AtualizarHorario;
import agendamento.servico.dto.CadastroHorario;
import agendamento.servico.dto.FiltroHorario;
import agendamento.servico.dto.RegistroHorario;
import agendamento.servico.entity.Agenda;
import agendamento.servico.entity.Barbeiro;
import agendamento.servico.entity.Horario;
import agendamento.servico.repository.AgendaRepository;
import agendamento.servico.repository.BarbeiroRepository;
import agendamento.servico.repository.HorarioRepository;
import agendamento.servico.service.HorarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class HorarioServiceImpl implements HorarioService {

    private final HorarioRepository horarioRepository;
    private final AgendaRepository agendaRepository;
    private final BarbeiroRepository barbeiroRepository;

    public HorarioServiceImpl(HorarioRepository horarioRepository, AgendaRepository agendaRepository, BarbeiroRepository barbeiroRepository) {
        this.horarioRepository = horarioRepository;
        this.agendaRepository = agendaRepository;
        this.barbeiroRepository = barbeiroRepository;
    }


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

    @Override
    public List<RegistroHorario> buscarHorariosPorFiltro (FiltroHorario horario) {
        if (horario.id() != null) {
            return Collections.singletonList(buscarHorario(horario.id()));
        }
        if (horario.horarioInicio() != null) {
            return HorarioAdapter.converter(this.horarioRepository.findAllByHorarioInicio(horario.horarioInicio()));
        }
        if (horario.horarioFim() != null) {
            return HorarioAdapter.converter(this.horarioRepository.findAllByHorarioFim(horario.horarioFim()));
        }
        return HorarioAdapter.converter(this.horarioRepository.findAll());
    }

    private Horario buscarHorarioPorId(Long id) {
        Horario horario = this.horarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro de horario nao existe"));
        if(horario.getDeletedAt() != null){throw new RuntimeException("Registro de horario nao existe");};
        return horario;
    }

    private Barbeiro buscarBarbeiroPorId(Long id){
        return this.barbeiroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro de barbeiro nao existe"));
    }

    public List<RegistroHorario> listarHorariosDisponiveisNoDia(LocalDate dia, Long id) {



        Barbeiro barbeiro = buscarBarbeiroPorId(id);
        Set<Long> horariosDoBarbeiroIds = barbeiro.getHorarioBarbeiro().stream()
                .map(hb -> hb.getHorario().getId())
                .collect(Collectors.toSet());

        List<Agenda> agendamentosNoDia = this.agendaRepository.findAllByDia(dia);

        Set<Long> horariosAgendadosIds = agendamentosNoDia.stream()
                .filter(agenda -> agenda.getDeletedAt() == null) // Apenas agendamentos ativos
                .map(agenda -> agenda.getHorario().getId())
                .collect(Collectors.toSet());

        List<Agenda> agendamentosDoBarbeiroNoDia = agendaRepository.findAllByDia(dia).stream()
                .filter(agenda -> agenda.getBarbeiro().getId().equals(id))
                .toList();

        Set<Long> horariosAgendadosParaBarbeiroIds = agendamentosDoBarbeiroNoDia.stream()
                .filter(agenda -> agenda.getDeletedAt() == null) // Apenas agendamentos ativos
                .map(agenda -> agenda.getHorario().getId())
                .collect(Collectors.toSet());

        return horarioRepository.findByDeletedAtIsNull().stream() // Pega todos os horários ativos do sistema
                .filter(horario -> horariosDoBarbeiroIds.contains(horario.getId())) // Filtra pelos horários que o barbeiro oferece
                .filter(horario -> !horariosAgendadosParaBarbeiroIds.contains(horario.getId())) // Filtra pelos que não estão agendados
                .map(HorarioAdapter::fromEntityToRegistroHorario)
                .collect(Collectors.toList());

    }
}
