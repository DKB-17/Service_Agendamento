package agendamento.servico.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

@Table(name= "agendas")
@Entity(name = "agenda")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "barbeiro_id")
    private Barbeiro barbeiro;
    @ManyToOne
    @JoinColumn(name = "horario_id")
    private Horario horario;
    @ManyToOne
    @JoinColumn(name = "servico_id")
    private Servico servico;
    @ManyToOne
    @JoinColumn(name = "caixa_id")
    private Caixa caixa;
    private Date dia;
    private BigDecimal valor;
    private Etapa etapa;
    @Column(name = "deleted_at")
    private Instant deletedAt;
    @Column(name = "updated_at")
    private Instant updatedAt;
    @Column(name = "created_at")
    private Instant createdAt;

}
