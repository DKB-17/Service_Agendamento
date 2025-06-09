package agendamento.servico.repository;

import agendamento.servico.entity.HorarioInvalido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HorarioInvalidoRepository extends JpaRepository<HorarioInvalido, Long> {
}
