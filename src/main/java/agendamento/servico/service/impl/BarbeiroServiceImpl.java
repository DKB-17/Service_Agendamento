package agendamento.servico.service.impl;

import agendamento.servico.adapter.BarbeiroAdapter;
import agendamento.servico.dto.AtualizarBarbeiro;
import agendamento.servico.dto.CadastroBarbeiro;
import agendamento.servico.dto.RegistroBarbeiro;
import agendamento.servico.entity.*;
import agendamento.servico.repository.*;
import agendamento.servico.service.BarbeiroService;
import agendamento.servico.service.ServicoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;

@Service
@AllArgsConstructor
public class BarbeiroServiceImpl implements BarbeiroService {

    private BarbeiroRepository barbeiroRepository;
    private ServicoRepository servicoRepository;
    private HorarioRepository horarioRepository;
    private ImagemRepository imagemRepository;
    private ServicoBarbeiroRepository servicoBarbeiroRepository;
    private HorarioBarbeiroRepository horarioBarbeiroRepository;

    @Override
    @Transactional
    public RegistroBarbeiro cadastrarBarbeiro(CadastroBarbeiro dados) {

        Imagem imagem = new Imagem(null, dados.caminhoImagem());
        imagem = this.imagemRepository.save(imagem);

        Barbeiro barbeiro = new Barbeiro(
                null,
                dados.nome(),
                imagem,
                new ArrayList<>(),
                new ArrayList<>(),
                null,
                Instant.now(),
                Instant.now()
        );

        barbeiro = this.barbeiroRepository.save(barbeiro);

        if (dados.servicos() != null && !dados.servicos().isEmpty()) {

            List<Servico> servicosExistentes = servicoRepository.findAllById(dados.servicos());

            for (Servico servico : servicosExistentes) {
                if (servico.getDeletedAt() == null) {
                    ServicoBarbeiro servicoBarbeiro = new ServicoBarbeiro(
                            servico,
                            barbeiro
                    );
                    barbeiro.getServicoBarbeiro().add(servicoBarbeiro);
                    servico.getServicoBarbeiro().add(servicoBarbeiro);
                    this.servicoBarbeiroRepository.save(servicoBarbeiro);
                }
            }

        }


        if (dados.horarios() != null && !dados.horarios().isEmpty()) {
            List<Horario> horariosExistentes = horarioRepository.findAllById(dados.horarios());

            for (Horario horario : horariosExistentes) {
                if (horario.getDeletedAt() == null) {
                    HorarioBarbeiro horarioBarbeiro = new HorarioBarbeiro(
                            horario,
                            barbeiro
                    );
                    barbeiro.getHorarioBarbeiro().add(horarioBarbeiro);
                    horario.getHorarioBarbeiro().add(horarioBarbeiro);
                    this.horarioBarbeiroRepository.save(horarioBarbeiro);
                }
            }

        }

        barbeiro = this.barbeiroRepository.save(barbeiro);
        return BarbeiroAdapter.fromEntityToRegistroBarbeiro(barbeiro);
    }

    @Override
    public List<RegistroBarbeiro> listarBarbeiros() {
        return this.barbeiroRepository.findByDeletedAtIsNull().stream().map(BarbeiroAdapter::fromEntityToRegistroBarbeiro).toList();
    }

    @Override
    public RegistroBarbeiro buscarBarbeiro(Long id) {
        Optional<Barbeiro> barbeiro = this.barbeiroRepository.findById(id);
        if (barbeiro.isEmpty() || barbeiro.get().getDeletedAt() != null) {
            throw new RuntimeException("Registro de barbeiro nao existe");
        }else{
            return BarbeiroAdapter.fromEntityToRegistroBarbeiro(barbeiro.get());
        }
    }

    @Override
    public RegistroBarbeiro atualizarBarbeiro(AtualizarBarbeiro dados) {
        Optional<Barbeiro> barbeiro = this.barbeiroRepository.findById(dados.id());
        if (barbeiro.isEmpty() || barbeiro.get().getDeletedAt() != null) {
            throw new RuntimeException("Registro de barbeiro nao existe");
        } else {
            if (dados.nome() != null && !dados.nome().isBlank()){
                barbeiro.get().setNome(dados.nome());
            }
            if (dados.caminhoImagem() != null && !dados.caminhoImagem().isBlank()){
                barbeiro.get().getImagem().setCaminho(dados.caminhoImagem());
            }
            if (dados.servicos() != null && !dados.servicos().isEmpty()) {
                List<Servico> servicosExistentes = servicoRepository.findAllById(dados.servicos());

                for (Servico servico : servicosExistentes) {
                    if (servico.getDeletedAt() == null) {
                        ServicoBarbeiro servicoBarbeiro = new ServicoBarbeiro(
                                servico,
                                barbeiro.get()
                        );
                        barbeiro.get().getServicoBarbeiro().add(servicoBarbeiro);
                        servico.getServicoBarbeiro().add(servicoBarbeiro);
                        this.servicoBarbeiroRepository.save(servicoBarbeiro);
                    }
                }
            }
            if (dados.horarios() != null && !dados.horarios().isEmpty()) {
                List<Horario> horariosExistentes = horarioRepository.findAllById(dados.horarios());

                for (Horario horario : horariosExistentes) {
                    if (horario.getDeletedAt() == null) {
                        HorarioBarbeiro horarioBarbeiro = new HorarioBarbeiro(
                                horario,
                                barbeiro.get()
                        );
                        barbeiro.get().getHorarioBarbeiro().add(horarioBarbeiro);
                        horario.getHorarioBarbeiro().add(horarioBarbeiro);
                        this.horarioBarbeiroRepository.save(horarioBarbeiro);
                    }
                }
            }
            barbeiro.get().setUpdatedAt(Instant.now());
            return BarbeiroAdapter.fromEntityToRegistroBarbeiro(this.barbeiroRepository.save(barbeiro.get()));
        }
    }

    @Override
    public void desativarBarbeiro(Long id) {
        Optional<Barbeiro> barbeiro = this.barbeiroRepository.findById(id);
        if (barbeiro.isEmpty() || barbeiro.get().getDeletedAt() != null){
            throw new RuntimeException("Registro de barbeiro nao existe");
        } else {
            barbeiro.get().setDeletedAt(Instant.now());
        }
    }

    @Override
    public RegistroBarbeiro ativarBarbeiro(Long id) {
        Optional<Barbeiro> barbeiro = this.barbeiroRepository.findById(id);
        if (barbeiro.isEmpty()){
            throw new RuntimeException("Registro de barbeiro nao existe");
        } else {
            barbeiro.get().setDeletedAt(null);
            return BarbeiroAdapter.fromEntityToRegistroBarbeiro(this.barbeiroRepository.save(barbeiro.get()));
        }
    }


}
