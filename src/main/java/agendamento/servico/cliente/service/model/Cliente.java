package agendamento.servico.cliente.service.model;


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
    private String nome;
    private String cpf;
    private String contato;
    @Column(name = "delete_at")
    private Instant deleteAt;
    @Column(name = "update_at")
    private Instant updateAt;
    @Column(name = "create_at")
    private Instant createAt;
}
