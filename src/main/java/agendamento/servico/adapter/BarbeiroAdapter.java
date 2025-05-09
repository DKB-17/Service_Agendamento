package agendamento.servico.adapter;

import agendamento.servico.dto.CadastroBarbeiro;
import agendamento.servico.dto.RegistroBarbeiro;
import agendamento.servico.entity.Barbeiro;
import agendamento.servico.entity.Imagem;

import java.time.Instant;

public class BarbeiroAdapter {
    public static Barbeiro fromCadastroBarbeiroToEntity(CadastroBarbeiro dados){
        Barbeiro barbeiro = new Barbeiro(
            null,
            dados.nome(),
            null,
            Instant.now(),
            Instant.now()
        );
        barbeiro.setImagem(new Imagem(
                null,
                dados.caminhoImagem()
        ));
        return barbeiro;
    }

    public static RegistroBarbeiro fromEntityToRegistroBarbeiro(Barbeiro barbeiro) {
        return new RegistroBarbeiro(
                barbeiro.getId(),
                barbeiro.getNome(),
                barbeiro.getImagem().getCaminho(),
                barbeiro.getCreatedAt()
        );
    }
}
