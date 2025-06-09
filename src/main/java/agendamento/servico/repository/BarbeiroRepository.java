package agendamento.servico.repository;

import agendamento.servico.entity.Barbeiro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BarbeiroRepository extends JpaRepository<Barbeiro, Long> {

        List<Barbeiro> findByDeletedAtIsNull();

}
