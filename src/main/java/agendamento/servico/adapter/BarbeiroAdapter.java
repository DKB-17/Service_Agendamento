package agendamento.servico.adapter;

import agendamento.servico.dto.AtualizarBarbeiro;
import agendamento.servico.dto.CadastroBarbeiro;
import agendamento.servico.dto.RegistroBarbeiro;
import agendamento.servico.entity.Barbeiro;

import java.time.Instant;
import java.util.Optional;

public class BarbeiroAdapter {
    public static Barbeiro fromCadastroBarbeiroToEntity(CadastroBarbeiro dados){
        return new Barbeiro(
                null,
                dados.nome(),
                dados.caminhoImagem(),
                null,
                Instant.now(),
                Instant.now()
        );
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
