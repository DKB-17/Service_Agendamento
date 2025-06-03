package agendamento.servico.repository;

import agendamento.servico.entity.Caixa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface CaixaRepository extends JpaRepository<Caixa, Long> {
    Optional<Caixa> findByDia(Date dia);
}
