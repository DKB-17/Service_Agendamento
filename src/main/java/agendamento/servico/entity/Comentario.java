package agendamento.servico.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity(name = "comentario")
@Table(name = "Comentarios")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "texto")
    private String texto;

    @Column(name = "qtd_curtidas")
    private Integer qtdCurtidas;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "comentario_id")
    private Comentario comentario;

    @Column(name = "created_at")
    private Instant createdAt;

}
