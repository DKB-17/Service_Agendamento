package agendamento.servico.repository;

import agendamento.servico.entity.Agenda;
import agendamento.servico.entity.Barbeiro;
import agendamento.servico.entity.enums.Etapa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    List<Agenda> findAllByUsuario_Nome(String nome);
    List<Agenda> findAllByDia(LocalDate dia);
    List<Agenda> findAllByValor(BigDecimal valor);
    List<Agenda> findAllByUsuario_Contato(String contato);
    List<Agenda> findAllByEtapa(Etapa etapa);

}
