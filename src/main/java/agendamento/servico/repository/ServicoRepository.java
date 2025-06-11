package agendamento.servico.repository;

import agendamento.servico.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
    List<Servico> findByDeletedAtIsNull();
    List<Servico> findAllByDescricao(String descricao);
    List<Servico> findAllByValor(BigDecimal valor);
}
