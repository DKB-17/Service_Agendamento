package agendamento.servico.adapter;

import agendamento.servico.dto.RegistroCurtida;
import agendamento.servico.dto.RegistroPost;
import agendamento.servico.entity.Post;

import java.util.List;

public class PostAdapter {

    public static RegistroPost fromEntityToRegistroPost(Post post, List<RegistroCurtida> curtidas) {
        return new RegistroPost(
                post.getId(),
                ClienteAdapter.fromEntityToRegistroCliente(post.getCliente()),
                post.getLegenda(),
                post.getAvaliacao(),
                post.getQtd_curtidas(),
                curtidas,
                post.getUpdatedAt(),
                post.getCreatedAt()
        );
    }

}
