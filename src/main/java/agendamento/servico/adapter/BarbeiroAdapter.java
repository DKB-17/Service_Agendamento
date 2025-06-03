package agendamento.servico.adapter;

import agendamento.servico.dto.*;
import agendamento.servico.entity.Barbeiro;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BarbeiroAdapter {


    public static RegistroBarbeiro fromEntityToRegistroBarbeiro(Barbeiro barbeiro) {

        String caminhoImagem = (barbeiro.getImagem() != null) ? barbeiro.getImagem().getBase64Imagem() : null;

        Set<RegistroServico> servicos = barbeiro.getServicoBarbeiro() != null
                ? barbeiro.getServicoBarbeiro().stream()
                .map(sb -> new RegistroServico(
                        sb.getServico().getId(),
                        sb.getServico().getDescricao(), // Pega o nome do Servico associado
                        sb.getServico().getValor(),
                        sb.getServico().getCreatedAt(),
                        sb.getServico().getUpdatedAt()))
                .collect(Collectors.toSet())
                : Collections.emptySet();

        Set<RegistroHorario> horarios = barbeiro.getHorarioBarbeiro() != null
                ? barbeiro.getHorarioBarbeiro().stream()
                .map(hb -> new RegistroHorario(
                        hb.getHorario().getId(),
                        hb.getHorario().getHorarioInicio(),
                        hb.getHorario().getHorarioFim(),
                        hb.getHorario().getCreatedAt(),
                        hb.getHorario().getUpdatedAt()))
                .collect(Collectors.toSet())
                : Collections.emptySet();

        return new RegistroBarbeiro(
                barbeiro.getId(),
                barbeiro.getNome(),
                caminhoImagem,
                servicos,
                horarios,
                barbeiro.getCreatedAt(),
                barbeiro.getUpdatedAt()
        );
    }
    public static RegistroBarbeiroAgenda fromEntityToRegistroBarbeiroAgenda(Barbeiro barbeiro) {

        String caminhoImagem = (barbeiro.getImagem() != null) ? barbeiro.getImagem().getBase64Imagem() : null;

        return new RegistroBarbeiroAgenda(
                barbeiro.getId(),
                barbeiro.getNome(),
                caminhoImagem,
                barbeiro.getCreatedAt(),
                barbeiro.getUpdatedAt()
        );
    }
}
