package agendamento.servico.controller;

import agendamento.servico.dto.CadastroPost;
import agendamento.servico.dto.CurtirPost;
import agendamento.servico.dto.RegistroPost;
import agendamento.servico.entity.Post;
import agendamento.servico.service.PostService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@CrossOrigin
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(@Lazy PostService postService) {
        this.postService = postService;
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

}
