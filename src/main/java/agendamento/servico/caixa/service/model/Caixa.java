package agendamento.servico.caixa.service.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Table(name = "caixas")
@Entity(name = "Caixa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Caixa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float lucro;
    @Column(name = "delete_at")
    private Instant deleteAt;
    @Column(name = "update_at")
    private Instant updateAt;
    @Column(name = "create_at")
    private Instant createAt;
}
