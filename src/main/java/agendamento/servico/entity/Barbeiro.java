package agendamento.servico.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Table(name = "barbeiros")
@Entity(name = "Barbeiro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Barbeiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "imagem_id")
    private Imagem imagem;

    @OneToMany(mappedBy = "barbeiro")
    private List<ServicoBarbeiro> servicoBarbeiro;

    @OneToMany(mappedBy = "barbeiro")
    private List<HorarioBarbeiro> horarioBarbeiro;

    @Column(name = "deleted_at")
    private Instant deletedAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "created_at")
    private Instant createdAt;
}
