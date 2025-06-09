package agendamento.servico.service.impl;

import agendamento.servico.adapter.AgendaAdapter;
import agendamento.servico.adapter.CaixaAdapter;
import agendamento.servico.adapter.UsuarioAdapter;
import agendamento.servico.dto.AtualizarAgenda;
import agendamento.servico.dto.CadastroAgenda;
import agendamento.servico.dto.FiltroAgenda;
import agendamento.servico.dto.RegistroAgenda;
import agendamento.servico.entity.*;
import agendamento.servico.entity.enums.Etapa;
import agendamento.servico.repository.*;
import agendamento.servico.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AgendaServiceImpl implements AgendaService {

    private final AgendaRepository agendaRepository;
    private final UsuarioRepository usuarioRepository;
    private final BarbeiroRepository barbeiroRepository;
    private final HorarioRepository horarioRepository;
    private final ServicoRepository servicoRepository;
    private final CaixaRepository caixaRepository;
    private final HorarioInvalidoRepository horarioInvalidoRepository;

    @Autowired
    public AgendaServiceImpl(
            AgendaRepository agendaRepository,
            UsuarioRepository usuarioRepository,
            BarbeiroRepository barbeiroRepository,
            HorarioRepository horarioRepository,
            ServicoRepository servicoRepository,
            CaixaRepository caixaRepository, HorarioInvalidoRepository horarioInvalidoRepository){
        this.agendaRepository = agendaRepository;
        this.usuarioRepository = usuarioRepository;
        this.barbeiroRepository = barbeiroRepository;
        this.horarioRepository = horarioRepository;
        this.servicoRepository = servicoRepository;
        this.caixaRepository = caixaRepository;
        this.horarioInvalidoRepository = horarioInvalidoRepository;
    }

    @Override
    @Transactional
    public RegistroAgenda cadastrarAgenda(CadastroAgenda dados) {


        Usuario usuario = this.buscarUsuario(dados);
        Barbeiro barbeiro = this.buscarBarbeiro(dados.barbeiroId());
        Horario horario = this.buscarHorario(dados.horarioId());
        Servico servico = this.buscarServico(dados.servicoId());
        List<HorarioInvalido> hrInvalidos = this.horarioInvalidoRepository.findAll();

        boolean horarioDisponivel = hrInvalidos.stream().anyMatch(h -> Objects.equals(h.getHorario().getId(), horario.getId()));
            
        boolean horarioValido = barbeiro.getHorarioBarbeiro().stream()
                .anyMatch(hb -> Objects.equals(hb.getHorario().getId(), horario.getId()));

        boolean servicoValido = barbeiro.getServicoBarbeiro().stream()
                .anyMatch(servicoBarbeiro -> Objects.equals(servicoBarbeiro.getServico().getId(), servico.getId()));

        this.validarHorarioEServico(horarioValido, servicoValido,horarioDisponivel);

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

        HorarioInvalido horarioInvalido = new HorarioInvalido(
                null,
                horario
        );
        this.horarioInvalidoRepository.save(horarioInvalido);

        return AgendaAdapter.fromEntityToRegistroAgenda(this.agendaRepository.save(agenda));
    }

    @Override
    public List<RegistroAgenda> listarAgendas() {
        return this.agendaRepository.findAll().stream().map(AgendaAdapter::fromEntityToRegistroAgenda).collect(Collectors.toList());
    }

    @Override
    public RegistroAgenda buscarAgenda(Long id) {
        Agenda agenda = buscarAgendaPorId(id);

        return AgendaAdapter.fromEntityToRegistroAgenda(agenda);
    }

    @Override
    public RegistroAgenda alterarAgenda(Long id, AtualizarAgenda agenda) {
        Agenda ag = buscarAgendaPorId(id);
        Caixa cx = buscarCaixaPorId(ag.getCaixa().getId());
        Etapa ne = Etapa.valueOf(agenda.etapa());
        this.caixaRepository.save(atualizaCaixa(ag, cx, ne));
        ag.setEtapa(ne);
        return  AgendaAdapter.fromEntityToRegistroAgenda(this.agendaRepository.save(ag));
    }

    @Override
    public List<RegistroAgenda> buscarAgendaPorFiltro(FiltroAgenda agenda) {
        if (agenda.id() != null) {
            return Collections.singletonList(buscarAgenda(agenda.id()));
        }
        if (agenda.nome() != null) {
            return Collections.singletonList(AgendaAdapter.fromEntityToRegistroAgenda(this.agendaRepository.findByUsuario_Nome(agenda.nome())));
        }
        if (agenda.dia() != null) {
            return Collections.singletonList(AgendaAdapter.fromEntityToRegistroAgenda(this.agendaRepository.findByDia(agenda.dia())));
        }
        if (agenda.valor() != null) {
            return Collections.singletonList(AgendaAdapter.fromEntityToRegistroAgenda(this.agendaRepository.findByValor(agenda.valor())));
        }
        if (agenda.contato() != null) {
            return Collections.singletonList(AgendaAdapter.fromEntityToRegistroAgenda(this.agendaRepository.findByUsuario_Contato(agenda.contato())));
        }
        if (agenda.etapa() != null) {
            return Collections.singletonList(AgendaAdapter.fromEntityToRegistroAgenda(this.agendaRepository.findByEtapa(agenda.etapa())));
        }
        return this.agendaRepository.findAll().stream().map(AgendaAdapter::fromEntityToRegistroAgenda).collect(Collectors.toList());
    }


    @Override
    public void desativarAgenda(Long id) {
        Agenda agenda = buscarAgendaPorId(id);

        agenda.setDeletedAt(Instant.now());

        this.agendaRepository.save(agenda);
    }
    
    private void validarHorarioEServico(Boolean horario, Boolean servico, Boolean horarioDisponivel) {
        if (!servico) {
            throw new RuntimeException("Servico nao disponivel para esse barbeiro");
        }
        if (!horario) {
            throw new RuntimeException("Horario nao disponivel para esse barbeiro");
        }
        if (!horarioDisponivel) {
            throw new RuntimeException("Horario nao disponivel para esse dia");
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

    private Servico buscarServico(Long idServico) {
        return this.servicoRepository.findById(idServico)
                .orElseThrow(() -> new RuntimeException("Registro de servico nao existe"));
    }

    private Agenda buscarAgendaPorId(Long id) {
        Agenda agenda = this.agendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro de agendamento nao existe"));
        if(agenda.getDeletedAt() != null){throw new RuntimeException("Registro de agendamento nao existe");};
        return agenda;
    }

    private Caixa buscarCaixaPorId(Long id) {
        Caixa cx = this.caixaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro de caixa nao existe"));
        return cx;
    }

    private Caixa atualizaCaixa(Agenda ag, Caixa cx, Etapa ne){
        if(ag.getEtapa() != ne){
            if(ne == Etapa.CONCLUIDO){
                cx.setLucro(cx.getLucro().add(ag.getValor()));
            } else if (ag.getEtapa() == Etapa.CONCLUIDO) {
                cx.setLucro(cx.getLucro().subtract(ag.getValor()));
            }
            return cx;
        }
        return cx;
    }
}
