package agendamento.servico.service.impl;

import agendamento.servico.adapter.AgendaAdapter;
import agendamento.servico.adapter.CaixaAdapter;
import agendamento.servico.adapter.UsuarioAdapter;
import agendamento.servico.dto.CadastroAgenda;
import agendamento.servico.dto.RegistroAgenda;
import agendamento.servico.entity.*;
import agendamento.servico.repository.*;
import agendamento.servico.service.AgendaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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


        Optional<Usuario> usuario = this.usuarioRepository.findByContato(dados.contato());
        if (usuario.isEmpty()) {
            usuario = Optional.of(this.usuarioRepository.save(UsuarioAdapter.fromCadastroAgendaToEntity(dados)));
        }


        Optional<Barbeiro> barbeiro = this.barbeiroRepository.findById(dados.barbeiroId());
        if (barbeiro.isEmpty()) {
            throw new RuntimeException("Registro de barbeiro nao existe");
        }

        Optional<Horario> horario = this.horarioRepository.findById(dados.horarioId());
        if (horario.isEmpty()) {
            throw new RuntimeException("Registro de horario nao existe");
        } else {
            boolean horarioValido = barbeiro.get().getHorarioBarbeiro().stream()
                    .anyMatch(hb -> {
                        return Objects.equals(hb.getHorario().getId(), horario.get().getId());
                    });
            if (!horarioValido) {
                throw new RuntimeException("Horario nao disponivel para esse barbeiro");
            }
        }

        Optional<Servico> servico = this.servicoRepository.findById(dados.servicoId());
        if (servico.isEmpty()) {
            throw new RuntimeException("Registro de servico nao existe");
        } else {
            boolean servicoValido = barbeiro.get().getServicoBarbeiro().stream()
                    .anyMatch(sb -> {
                        return Objects.equals(sb.getServico().getId(), servico.get().getId());
                    });
            if (!servicoValido) {
                throw new RuntimeException("Servico nao disponivel para esse barbeiro");
            }
        }

        Optional<Caixa> caixa = this.caixaRepository.findByDia(dados.dia());
        if (caixa.isEmpty()) {
            caixa = Optional.of(this.caixaRepository.save(CaixaAdapter.fromCadastroAgendaToEntity(dados)));
        }

        Agenda agenda = new Agenda(
                null,
                usuario.get(),
                barbeiro.get(),
                horario.get(),
                servico.get(),
                caixa.get(),
                dados.dia(),
                servico.get().getValor(),
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
}
