package agendamento.servico.repository;

import agendamento.servico.entity.Comentario;
import agendamento.servico.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findAllByPost(Post post);
}
