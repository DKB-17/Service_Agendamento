package agendamento.servico.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Table(name = "barbeiros")
@Entity(name = "Barbeiro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Barbeiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(name = "link_imagem")
    private String caminhoImagem;
    @Column(name = "deleted_at")
    private Instant deleteAt;
    @Column(name = "updated_at")
    private Instant updateAt;
    @Column(name = "created_at")
    private Instant createAt;
}
