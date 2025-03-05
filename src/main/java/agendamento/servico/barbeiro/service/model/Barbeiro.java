package agendamento.servico.barbeiro.service.model;


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
    @Column(name = "delete_at")
    private Instant deleteAt;
    @Column(name = "update_at")
    private Instant updateAt;
    @Column(name = "create_at")
    private Instant createAt;
}
