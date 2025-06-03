package agendamento.servico.service.impl;

import agendamento.servico.adapter.AgendaAdapter;
import agendamento.servico.adapter.CaixaAdapter;
import agendamento.servico.adapter.UsuarioAdapter;
import agendamento.servico.dto.CadastroAgenda;
import agendamento.servico.dto.RegistroAgenda;
import agendamento.servico.entity.*;
import agendamento.servico.entity.enums.Etapa;
import agendamento.servico.repository.*;
import agendamento.servico.service.AgendaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AgendaServiceImpl implements AgendaService {

    private AgendaRepository agendaRepository;
    private UsuarioRepository usuarioRepository;
    private BarbeiroRepository barbeiroRepository;
    private HorarioRepository horarioRepository;
    private ServicoRepository servicoRepository;
    private CaixaRepository caixaRepository;

    @Override
    public RegistroAgenda cadastrarAgenda(CadastroAgenda dados) {

        Usuario usuario = this.buscarUsuario(dados);
        Barbeiro barbeiro = this.buscarBarbeiro(dados.barbeiroId());
        Horario horario = this.buscarHorario(dados.horarioId());
                
            
        boolean horarioValido = barbeiro.getHorarioBarbeiro().stream()
                .anyMatch(hb -> Objects.equals(hb.getHorario().getId(), horario.getId()));


        Servico servico = this.servicoRepository.findById(dados.servicoId())
                .orElseThrow(() -> new RuntimeException("Registro de servico nao existe"));

        boolean servicoValido = barbeiro.getServicoBarbeiro().stream()
                .anyMatch(sb -> Objects.equals(sb.getServico().getId(), servico.getId()));

        this.validarHorarioEServico(horarioValido, servicoValido);

        Caixa caixa = this.caixaRepository.findByDia(dados.dia())
                .orElse(this.caixaRepository.save(CaixaAdapter.fromCadastroAgendaToEntity(dados)));

        Agenda agenda = new Agenda(
                null,
                usuario,
                barbeiro,
                horario,
                servico,
                caixa,
                dados.dia(),
                servico.getValor(),
                Etapa.PENDENTE,
                null,
                Instant.now(),
                Instant.now()
        );

        return AgendaAdapter.fromEntityToRegistroAgenda(this.agendaRepository.save(agenda));
    }

    @Override
    public List<RegistroAgenda> listarAgendas() {
        return List.of();
    }

    @Override
    public RegistroAgenda buscarAgenda(Long id) {
        return null;
    }

    @Override
    public RegistroAgenda atualizarAgenda(RegistroAgenda agenda) {
        return null;
    }

    @Override
    public void excluirAgenda(Long id) {

    }
    
    private void validarHorarioEServico(Boolean horario, Boolean servico) {
        if (!servico) {
            throw new RuntimeException("Servico nao disponivel para esse barbeiro");
        }
        if (!horario) {
            throw new RuntimeException("Horario nao disponivel para esse barbeiro");
        }
    }
    
    private Usuario buscarUsuario(CadastroAgenda dados) {
        return this.usuarioRepository.findByContato(dados.contato())
                .orElse(this.usuarioRepository.save(UsuarioAdapter.fromCadastroAgendaToEntity(dados)));
    }
    
    private Barbeiro buscarBarbeiro(Long idBarbeiro) {
        return this.barbeiroRepository.findById(idBarbeiro)
                .orElseThrow(() -> new RuntimeException("Registro de barbeiro nao existe"));
    }
    
    private Horario buscarHorario(Long idHorario) {
        return this.horarioRepository.findById(idHorario)
                .orElseThrow(() -> new RuntimeException("Registro de horario nao existe"));
    }
}
