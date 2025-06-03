package agendamento.servico.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;


@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HorarioBarbeiroId implements Serializable {

    @Column(name = "horario_id")
    private Long horarioId;

    @Column(name = "barbeiro_id")
    private Long barbeiroId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HorarioBarbeiroId that = (HorarioBarbeiroId) o;
        return Objects.equals(barbeiroId, that.barbeiroId) &&
                Objects.equals(horarioId, that.horarioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(barbeiroId, horarioId);
    }

}
