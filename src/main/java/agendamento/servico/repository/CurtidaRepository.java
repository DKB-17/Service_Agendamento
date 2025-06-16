package agendamento.servico.repository;

import agendamento.servico.entity.Cliente;
import agendamento.servico.entity.Curtida;
import agendamento.servico.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CurtidaRepository extends JpaRepository<Curtida, Long> {
    Curtida findByClienteAndPost(Cliente cliente, Post post);
    List<Curtida> findAllByPost(Post post);
}
