package agendamento.servico.repository;

import agendamento.servico.entity.DiaDaSemana;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiasDaSemanaRepository extends JpaRepository<DiaDaSemana, Long> {
}
