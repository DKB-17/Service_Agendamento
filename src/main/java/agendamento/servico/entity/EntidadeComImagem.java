package agendamento.servico.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class EntidadeComImagem {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "imagem_id")
    protected Imagem imagem;
}
