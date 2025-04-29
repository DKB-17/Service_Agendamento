package agendamento.servico.entity;


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
    private LocalTime horarioInicio;
    @Column(name = "fim")
    private LocalTime horarioFim;
    @Column(name = "deleted_at")
    private Instant deleteAt;
    @Column(name = "updated_at")
    private Instant updateAt;
    @Column(name = "created_at")
    private Instant createAt;

    public void atualizaHorario(){

    }

}
