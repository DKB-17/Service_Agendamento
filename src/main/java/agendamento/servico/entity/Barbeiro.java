package agendamento.servico.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Table(name = "barbeiros")
@Entity(name = "Barbeiro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Barbeiro extends EntidadeComImagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(name = "deleted_at")
    private Instant deletedAt;
    @Column(name = "updated_at")
    private Instant updatedAt;
    @Column(name = "created_at")
    private Instant createdAt;
}
