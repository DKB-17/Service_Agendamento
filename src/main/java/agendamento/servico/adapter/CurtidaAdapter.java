package agendamento.servico.adapter;

import agendamento.servico.dto.RegistroCurtida;
import agendamento.servico.dto.RegistroPost;
import agendamento.servico.entity.Curtida;

import java.util.List;
import java.util.stream.Collectors;

public class CurtidaAdapter {

    public static RegistroCurtida fromEntityToRegistroCurtida(Curtida entity) {
        if (entity.getPost() == null) return new RegistroCurtida(entity.getId(), entity.getCliente().getId(), null, entity.getComentario().getId());
        if (entity.getComentario() == null) return  new RegistroCurtida(entity.getId(), entity.getCliente().getId(), entity.getPost().getId(), null);

        return new RegistroCurtida(
                entity.getId(),
                entity.getCliente().getId(),
                null,
                null
        );
    }

    public static List<RegistroCurtida> converter(List<Curtida> curtidas){
        return curtidas.stream().map(CurtidaAdapter::fromEntityToRegistroCurtida).collect(Collectors.toList());
    }
}
