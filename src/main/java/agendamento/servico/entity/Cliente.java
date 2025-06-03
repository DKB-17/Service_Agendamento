package agendamento.servico.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Table(name = "clientes")
@Entity(name = "Cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "contato")
    private String contato;

    @Column(name = "delete_at")
    private Instant deleteAt;

    @Column(name = "update_at")
    private Instant updateAt;

    @Column(name = "create_at")
    private Instant createAt;
}
