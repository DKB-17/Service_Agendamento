package agendamento.servico.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "horario_invalido")
@Table(name = "horarios_invalidos")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HorarioInvalido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "horario_id")
    private Horario horario;

}
