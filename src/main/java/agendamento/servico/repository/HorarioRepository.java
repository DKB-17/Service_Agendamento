package agendamento.servico.repository;

import agendamento.servico.entity.Horario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.List;
import java.util.Set;

public interface HorarioRepository extends JpaRepository<Horario, Long> {
    List<Horario> findByDeletedAtIsNull();
    List<Horario> findAllByHorarioInicio(LocalTime horario);
    List<Horario> findAllByHorarioFim(LocalTime horario);
}
