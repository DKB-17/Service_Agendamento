package agendamento.servico.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Table(name = "dias_da_semana")
@Entity(name = "dia_da_semana")
@EqualsAndHashCode(of = "id")
@Getter
public class DiaDaSemana {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao")
    private String descricao;
}
