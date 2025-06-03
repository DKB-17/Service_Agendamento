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
public class ServicoBarbeiroId implements Serializable {

    @Column(name = "servico_id")
    private Long servicoId;

    @Column(name = "barbeiro_id")
    private Long barbeiroId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServicoBarbeiroId that = (ServicoBarbeiroId) o;
        return Objects.equals(barbeiroId, that.barbeiroId) &&
                Objects.equals(servicoId, that.servicoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(barbeiroId, servicoId);
    }

}
