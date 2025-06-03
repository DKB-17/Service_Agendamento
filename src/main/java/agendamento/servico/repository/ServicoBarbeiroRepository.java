package agendamento.servico.repository;

import agendamento.servico.entity.ServicoBarbeiro;
import agendamento.servico.entity.ServicoBarbeiroId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoBarbeiroRepository extends JpaRepository<ServicoBarbeiro, ServicoBarbeiroId> {
}
