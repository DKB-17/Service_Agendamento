package agendamento.servico.repository;

import agendamento.servico.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
    List<Servico> findByDeleteAtIsNull();
}
