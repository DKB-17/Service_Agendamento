package agendamento.servico.service.impl;

import agendamento.servico.adapter.ComentarioAdapter;
import agendamento.servico.dto.CadastroComentario;
import agendamento.servico.dto.RegistroComentario;
import agendamento.servico.entity.Cliente;
import agendamento.servico.entity.Comentario;
import agendamento.servico.entity.Post;
import agendamento.servico.repository.ClienteRepository;
import agendamento.servico.repository.ComentarioRepository;
import agendamento.servico.repository.PostRepository;
import agendamento.servico.service.ComentarioService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ComentarioServiceImpl implements ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final ClienteRepository clienteRepository;
    private final PostRepository postRepository;

    public ComentarioServiceImpl(@Lazy ComentarioRepository comentarioRepository, ClienteRepository clienteRepository, PostRepository postRepository) {
        this.comentarioRepository = comentarioRepository;
        this.clienteRepository = clienteRepository;
        this.postRepository = postRepository;
    }


    @Override
    public RegistroComentario cadastrarComentario(CadastroComentario cadastroComentario) {
        Cliente cliente = buscarClientePorId(cadastroComentario.cliente_id());

        Comentario comentarioC = new Comentario(
                null,
                cadastroComentario.texto(),
                0,
                cliente,
                null,
                null,
                Instant.now()
        );


        if (cadastroComentario.post_id()!= null) {
            Post post = buscarPostPorId(cadastroComentario.post_id());
            comentarioC.setPost(post);
        }

        if (cadastroComentario.comentario_id() != null) {
            Comentario comentario = buscarComentarioPorId(cadastroComentario.comentario_id());
            comentarioC.setComentario(comentario);
        }

        return ComentarioAdapter.fromEntityToRegistroComentario(this.comentarioRepository.save(comentarioC));
    }

    private Cliente buscarClientePorId(Long id) {
        return this.clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("O cliente nao existe"));
    }

    private Comentario buscarComentarioPorId(Long id) {
        return this.comentarioRepository.findById(id).orElseThrow(() -> new RuntimeException("O Comentario nao existe"));
    }

    private Post buscarPostPorId(Long id) {
        return this.postRepository.findById(id).orElseThrow(() -> new RuntimeException("O Post nao existe"));
    }

}
