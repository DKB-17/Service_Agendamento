package agendamento.servico.service;

import agendamento.servico.dto.CadastroComentario;
import agendamento.servico.dto.RegistroComentario;

public interface ComentarioService {

    RegistroComentario cadastrarComentario(CadastroComentario cadastroComentario);

}
