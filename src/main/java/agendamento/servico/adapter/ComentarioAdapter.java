package agendamento.servico.adapter;

import agendamento.servico.dto.CadastroCliente;
import agendamento.servico.dto.CadastroComentario;
import agendamento.servico.dto.RegistroComentario;
import agendamento.servico.entity.Comentario;

import java.util.Locale;

public class ComentarioAdapter {

    public static RegistroComentario fromEntityToRegistroComentario(Comentario comentario) {
        if (comentario.getComentario() == null) return new RegistroComentario(
                comentario.getId(),
                comentario.getTexto(),
                comentario.getQtdCurtidas(),
                comentario.getCliente().getId(),
                comentario.getPost().getId(),
                null,
                comentario.getCreatedAt()
        );
        if (comentario.getPost() == null) return new RegistroComentario(
                comentario.getId(),
                comentario.getTexto(),
                comentario.getQtdCurtidas(),
                comentario.getCliente().getId(),
                null,
                comentario.getComentario().getId(),
                comentario.getCreatedAt()
        );
        return new RegistroComentario(
                comentario.getId(),
                comentario.getTexto(),
                comentario.getQtdCurtidas(),
                comentario.getCliente().getId(),
                null,
                null,
                comentario.getCreatedAt()
        );

    }
}
