package agendamento.servico.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Table(name = "horarios")
@Entity(name = "Horario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "inicio")
    private LocalTime horarioInicio;
    @Column(name = "fim")
    private LocalTime horarioFim;

    @OneToMany(mappedBy = "horario")
    private List<HorarioBarbeiro> horarioBarbeiro;

    @Column(name = "deleted_at")
    private Instant deletedAt;
    @Column(name = "updated_at")
    private Instant updatedAt;
    @Column(name = "created_at")
    private Instant createdAt;
}
