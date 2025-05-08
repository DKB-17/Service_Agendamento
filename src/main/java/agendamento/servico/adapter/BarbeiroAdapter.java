package agendamento.servico.adapter;

import agendamento.servico.dto.CadastroBarbeiro;
import agendamento.servico.dto.RegistroBarbeiro;
import agendamento.servico.entity.Barbeiro;
import agendamento.servico.entity.Imagem;

import java.time.Instant;

public class BarbeiroAdapter {
    public static Barbeiro fromCadastroBarbeiroToEntity(CadastroBarbeiro dados){

        Imagem imagem = new Imagem();

        Barbeiro barbeiro = new Barbeiro(
            null,
            dados.nome(),
            null,
            Instant.now(),
            Instant.now()
                                        );

        return barbeiro;
    }

    public static RegistroBarbeiro fromEntityToRegistroBarbeiro(Barbeiro barbeiro) {
        return new RegistroBarbeiro(
                barbeiro.getId(),
                barbeiro.getNome(),
                barbeiro.getCaminhoImagem(),
                barbeiro.getCreateAt()
        );
    }
}
