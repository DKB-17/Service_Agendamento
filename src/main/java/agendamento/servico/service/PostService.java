package agendamento.servico.service;

import agendamento.servico.dto.CadastroPost;
import agendamento.servico.dto.CurtirPost;
import agendamento.servico.dto.RegistroComentario;
import agendamento.servico.dto.RegistroPost;

import java.util.List;

public interface PostService {

    RegistroPost cadastroPost(CadastroPost dados);
    List<RegistroPost> listarPosts();
    RegistroPost atualizarPost(Long idPost,CurtirPost dados);
    List<RegistroComentario> buscarComentariosPost (Long id);

}
