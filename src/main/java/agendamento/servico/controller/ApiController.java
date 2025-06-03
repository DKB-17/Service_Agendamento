package agendamento.servico.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("health")
@CrossOrigin
public class ApiController {

    @GetMapping
    public ResponseEntity index() {
        return ResponseEntity.ok().body("{\n" +
                "  \"success\": true,\n" +
                "  \"message\": \"Operação realizada com sucesso\"\n" +
                "}");
    }
}
