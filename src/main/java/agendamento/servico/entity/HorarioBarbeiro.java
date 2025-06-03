package agendamento.servico.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "horario_barbeiro")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HorarioBarbeiro {

    @EmbeddedId
    private HorarioBarbeiroId id;

    @ManyToOne
    @MapsId("horarioId")
    @JoinColumn(name = "horario_id")
    private Horario horario;

    @ManyToOne
    @MapsId("barbeiroId")
    @JoinColumn(name = "barbeiro_id")
    private Barbeiro barbeiro;

    public HorarioBarbeiro(Horario horario, Barbeiro barbeiro) {
        this.id = new HorarioBarbeiroId(horario.getId(), barbeiro.getId());
        this.horario = horario;
        this.barbeiro = barbeiro;
    }

}
