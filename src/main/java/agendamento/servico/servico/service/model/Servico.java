package agendamento.servico.servico.service.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Table(name = "servicos")
@Entity(name = "Servico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    @Column(name = "delete_at")
    private Instant deleteAt;
    @Column(name = "update_at")
    private Instant updateAt;
    @Column(name = "create_at")
    private Instant createAt;
}
