package agendamento.servico.repository;

import agendamento.servico.entity.HorarioBarbeiro;
import agendamento.servico.entity.HorarioBarbeiroId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HorarioBarbeiroRepository extends JpaRepository<HorarioBarbeiro, HorarioBarbeiroId> {
}
