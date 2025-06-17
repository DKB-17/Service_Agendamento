package agendamento.servico.controller;

import agendamento.servico.dto.*;
import agendamento.servico.entity.Comentario;
import agendamento.servico.entity.Post;
import agendamento.servico.service.ComentarioService;
import agendamento.servico.service.PostService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpRange;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@CrossOrigin
public class PostController {

    private final PostService postService;
    private final ComentarioService comentarioService;

    @Autowired
    public PostController(@Lazy PostService postService, ComentarioService comentarioService) {
        this.postService = postService;
        this.comentarioService = comentarioService;
    }

    @PostMapping()
    public ResponseEntity<RegistroPost> cadastrarPost(@RequestBody CadastroPost cadastroPost) {
        try {
            RegistroPost registroPost = this.postService.cadastroPost(cadastroPost);
            return new ResponseEntity<>(registroPost, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/{id}/curtir")
    public ResponseEntity<RegistroPost> curtirPost(@PathVariable Long id, @RequestBody CurtirPost curtida) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(this.postService.atualizarPost(id, curtida));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/{id}/descurtir")
    public ResponseEntity<RegistroPost> descurtirPost(@PathVariable Long id, @RequestBody CurtirPost curtida) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(this.postService.atualizarPost(id, curtida));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<List<RegistroPost>> listarPost() {
        List<RegistroPost> lista = this.postService.listarPosts();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @GetMapping("/{idpost}/comentarios")
    public ResponseEntity<List<RegistroComentario>> buscarComentatiosDoPost(@PathVariable Long idpost) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(this.postService.buscarComentariosPost(idpost));
        }   catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/comentario")
    public ResponseEntity<RegistroComentario> cadastrarComentario (@RequestBody CadastroComentario dados) {
        try{
            RegistroComentario rg = this.comentarioService.cadastrarComentario(dados);
            return ResponseEntity.status(HttpStatus.CREATED).body(rg);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}
