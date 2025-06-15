package agendamento.servico.service;

import agendamento.servico.dto.AtualizarHorario;
import agendamento.servico.dto.CadastroHorario;
import agendamento.servico.dto.FiltroHorario;
import agendamento.servico.dto.RegistroHorario;

import java.time.LocalDate;
import java.util.List;

public interface HorarioService {

     RegistroHorario cadastrarHorario(CadastroHorario dados);
     List<RegistroHorario> listarHorarios();
     RegistroHorario buscarHorario(Long id);
     RegistroHorario atualizarHorario(AtualizarHorario dados);
     void desativarHorario(Long id);
     RegistroHorario ativarHorario(Long id);
     List<RegistroHorario> buscarHorariosPorFiltro(FiltroHorario filtro);
     List<RegistroHorario> listarHorariosDisponiveisNoDia (LocalDate dia, Long id);

}
