package agendamento.servico.service;

import agendamento.servico.dto.AtualizarBarbeiro;
import agendamento.servico.dto.CadastroBarbeiro;
import agendamento.servico.dto.RegistroBarbeiro;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BarbeiroService {

   RegistroBarbeiro cadastrarBarbeiro(CadastroBarbeiro dados);
   List<RegistroBarbeiro> listarBarbeiros();
   RegistroBarbeiro buscarBarbeiro(Long id);
   RegistroBarbeiro atualizarBarbeiro(AtualizarBarbeiro dados);
   void desativarBarbeiro(Long id);
   RegistroBarbeiro ativarBarbeiro(Long id);
   byte[] buscarImagemDoBarbeiro(Long idBarbeiro);
   void atribuirImagemBarbeiro(MultipartFile file, Long idBarbeiro);
}
