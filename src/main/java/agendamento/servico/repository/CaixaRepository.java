package agendamento.servico.repository;

import agendamento.servico.entity.Caixa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CaixaRepository extends JpaRepository<Caixa, Long> {

    Optional<Caixa> findByDia(LocalDate dia);
    List<Caixa> findByDeleteAtIsNull();
    List<Caixa> findByDiaBetween(LocalDate d1, LocalDate d2);

    @Query("SELECT SUM(caixa.lucro) FROM Caixa caixa WHERE caixa.dia BETWEEN :mes1 AND :mes2")
    BigDecimal LucroDoMes(@Param("mes1") LocalDate mes1, @Param("mes2") LocalDate mes2);

}
