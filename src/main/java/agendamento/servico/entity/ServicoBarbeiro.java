package agendamento.servico.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "servico_barbeiro")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServicoBarbeiro {

    @EmbeddedId
    private ServicoBarbeiroId id;

    @ManyToOne
    @MapsId("servicoId")
    @JoinColumn(name = "servico_id")
    private Servico servico;

    @ManyToOne
    @MapsId("barbeiroId")
    @JoinColumn(name = "barbeiro_id")
    private Barbeiro barbeiro;

    public ServicoBarbeiro(Servico servico, Barbeiro barbeiro) {
        this.id = new ServicoBarbeiroId(servico.getId(), barbeiro.getId());
        this.servico = servico;
        this.barbeiro = barbeiro;
    }
}
