package agendamento.servico.repository;

import agendamento.servico.entity.Agenda;
import agendamento.servico.entity.enums.Etapa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    Agenda findByDeletedAtIsNull();
    Agenda findByUsuario_Nome(String nome);
    Agenda findByDia(LocalDate dia);
    Agenda findByValor(BigDecimal valor);
    Agenda findByUsuario_Contato(String contato);
    Agenda findByEtapa(Etapa etapa);
}
