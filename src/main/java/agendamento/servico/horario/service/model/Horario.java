package agendamento.servico.horario.service.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalTime;

@Table(name = "horarios")
@Entity(name = "Horario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "inicio")
    private LocalTime HoraInicio;
    @Column(name = "fim")
    private LocalTime HoraFim;
    @Column(name = "delete_at")
    private Instant deleteAt;
    @Column(name = "update_at")
    private Instant updateAt;
    @Column(name = "create_at")
    private Instant createAt;
}
