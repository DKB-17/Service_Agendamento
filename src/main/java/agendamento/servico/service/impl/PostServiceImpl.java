package agendamento.servico.service.impl;

import agendamento.servico.adapter.CurtidaAdapter;
import agendamento.servico.adapter.PostAdapter;
import agendamento.servico.dto.CadastroPost;
import agendamento.servico.dto.CurtirPost;
import agendamento.servico.dto.RegistroCurtida;
import agendamento.servico.dto.RegistroPost;
import agendamento.servico.entity.Agenda;
import agendamento.servico.entity.Cliente;
import agendamento.servico.entity.Curtida;
import agendamento.servico.entity.Post;
import agendamento.servico.repository.ClienteRepository;
import agendamento.servico.repository.CurtidaRepository;
import agendamento.servico.repository.PostRepository;
import agendamento.servico.service.PostService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ClienteRepository clienteRepository;
    private final CurtidaRepository curtidaRepository;


    public PostServiceImpl(PostRepository postRepository, ClienteRepository clienteRepository, CurtidaRepository curtidaRepository) {

        this.postRepository = postRepository;
        this.clienteRepository = clienteRepository;
        this.curtidaRepository = curtidaRepository;
    }

    @Override
    public RegistroPost cadastroPost(CadastroPost dados) {

        Cliente cliente = buscaClientePorId(dados.cliente_id());

        Post post = new Post(
                null,
                cliente,
                null,
                dados.legenda(),
                dados.avaliacao(),
                0,
                Instant.now(),
                Instant.now()
        );

        return PostAdapter.fromEntityToRegistroPost(this.postRepository.save(post), new ArrayList<>());
    }

    @Override
    public List<RegistroPost> listarPosts() {
        List<RegistroPost> listaPosts = new ArrayList<>();

        this.postRepository.findAll().stream().forEach(post -> {
            List<RegistroCurtida> lista = CurtidaAdapter.converter(this.curtidaRepository.findAllByPost(post));
            listaPosts.add(PostAdapter.fromEntityToRegistroPost(post, lista));
        });

        return listaPosts;
    }

    @Override
    public RegistroPost atualizarPost(Long id, CurtirPost dados) {
        Post post = buscaPostPorId(id);
        Cliente cliente = buscaClientePorId(dados.cliente_id());

        Curtida curtida = this.curtidaRepository.findByClienteAndPost(cliente, post);

        if (curtida == null) {
            Curtida like = new Curtida(
                    null,
                    cliente,
                    post,
                    null,
                    Instant.now()
            );
            this.curtidaRepository.save(like);
            post.setQtd_curtidas(post.getQtd_curtidas() + 1);
        }else{
            this.curtidaRepository.delete(curtida);
            post.setQtd_curtidas(post.getQtd_curtidas() - 1);
        }
        post = this.postRepository.save(post);
        return PostAdapter.fromEntityToRegistroPost(post, CurtidaAdapter.converter(this.curtidaRepository.findAllByPost(post)));

    }

    private Cliente buscaClientePorId(Long id) {
        return this.clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Registro de cliente nao existe"));
    }

    private Post buscaPostPorId(Long id) {
        return this.postRepository.findById(id).orElseThrow(() -> new RuntimeException("Registro de post nao existe"));
    }
}
