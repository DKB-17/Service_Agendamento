package agendamento.servico.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

@Table(name = "caixas")
@Entity(name = "Caixa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Caixa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lucro")
    private BigDecimal lucro;

    @Column(name = "dia")
    private Date dia;

    @Column(name = "deleted_at")
    private Instant deleteAt;

    @Column(name = "updated_at")
    private Instant updateAt;

    @Column(name = "created_at")
    private Instant createAt;
}
