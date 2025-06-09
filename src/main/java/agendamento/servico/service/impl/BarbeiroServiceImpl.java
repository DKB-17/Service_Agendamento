package agendamento.servico.service.impl;

import agendamento.servico.adapter.BarbeiroAdapter;
import agendamento.servico.dto.AtualizarBarbeiro;
import agendamento.servico.dto.CadastroBarbeiro;
import agendamento.servico.dto.RegistroBarbeiro;
import agendamento.servico.entity.*;
import agendamento.servico.repository.*;
import agendamento.servico.service.BarbeiroService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.*;

@Service
@AllArgsConstructor
public class BarbeiroServiceImpl implements BarbeiroService {

    private BarbeiroRepository barbeiroRepository;
    private ServicoRepository servicoRepository;
    private HorarioRepository horarioRepository;
    private ServicoBarbeiroRepository servicoBarbeiroRepository;
    private HorarioBarbeiroRepository horarioBarbeiroRepository;

    @Override
    @Transactional
    public RegistroBarbeiro cadastrarBarbeiro(CadastroBarbeiro dados) {
        Barbeiro barbeiro = new Barbeiro(
                null,
                dados.nome(),
                null,
                new ArrayList<>(),
                new ArrayList<>(),
                null,
                Instant.now(),
                Instant.now()
        );

        Barbeiro barbeiroSalvo = this.barbeiroRepository.save(barbeiro);

        List<Servico> servicosExistentes = servicoRepository.findAllById(dados.servicos());

        servicosExistentes.forEach(servico -> {
            if (servico.getDeletedAt() == null) {
                ServicoBarbeiro servicoBarbeiro = new ServicoBarbeiro(
                        servico,
                        barbeiroSalvo
                );
                barbeiroSalvo.getServicoBarbeiro().add(servicoBarbeiro);
                servico.getServicoBarbeiro().add(servicoBarbeiro);

            }
        });

        List<Horario> horariosExistentes = horarioRepository.findAllById(dados.horarios());

        horariosExistentes.forEach(horario -> {
            if (horario.getDeletedAt() == null) {
                HorarioBarbeiro horarioBarbeiro = new HorarioBarbeiro(
                        horario,
                        barbeiroSalvo
                );
                barbeiroSalvo.getHorarioBarbeiro().add(horarioBarbeiro);
                horario.getHorarioBarbeiro().add(horarioBarbeiro);
            }
        });

        Barbeiro barbeiroAlterado = this.barbeiroRepository.save(barbeiroSalvo);
        return BarbeiroAdapter.fromEntityToRegistroBarbeiro(barbeiroAlterado);
    }

    @Override
    public List<RegistroBarbeiro> listarBarbeiros() {
        return this.barbeiroRepository
                .findByDeletedAtIsNull()
                .stream()
                .map(BarbeiroAdapter::fromEntityToRegistroBarbeiro)
                .toList();
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

        Barbeiro barbeiro = this.buscarBarbeiroPorId(dados.id());

        barbeiro.setNome(dados.nome());

        if (dados.caminhoImagem() != null) {
            barbeiro.getImagem().setBase64Imagem(dados.caminhoImagem());
        }

        if (dados.servicos() != null && !dados.servicos().isEmpty()) {
            List<Servico> servicosExistentes = servicoRepository.findAllById(dados.servicos());

            for(ServicoBarbeiro sb: barbeiro.getServicoBarbeiro()) {
                boolean manter = false;
                for (Servico se : servicosExistentes) {
                    if (sb.getServico().getId().equals(se.getId())) {
                        servicosExistentes.remove(se);
                        manter = true;
                        break;
                    }
                }
                if (!manter) {
                    barbeiro.getServicoBarbeiro().remove(sb);
                }
            }

            for (Servico servico : servicosExistentes) {
                if (servico.getDeletedAt() == null) {
                    ServicoBarbeiro servicoBarbeiro = new ServicoBarbeiro(
                            servico,
                            barbeiro
                    );
                    barbeiro.getServicoBarbeiro().add(servicoBarbeiro);
                    servico.getServicoBarbeiro().add(servicoBarbeiro);
                }
            }
        }
        if (dados.horarios() != null && !dados.horarios().isEmpty()) {
            List<Horario> horariosExistentes = horarioRepository.findAllById(dados.horarios());

            for(HorarioBarbeiro hb: barbeiro.getHorarioBarbeiro()) {
                boolean manter = false;
                for (Horario he : horariosExistentes) {
                    if (hb.getHorario().getId().equals(he.getId())) {
                        horariosExistentes.remove(he);
                        manter = true;
                        break;
                    }
                }
                if (!manter) {
                    barbeiro.getHorarioBarbeiro().remove(hb);
                }
            }


            for (Horario horario : horariosExistentes) {
                if (horario.getDeletedAt() == null) {
                    HorarioBarbeiro horarioBarbeiro = new HorarioBarbeiro(
                            horario,
                            barbeiro
                    );
                    barbeiro.getHorarioBarbeiro().add(horarioBarbeiro);
                    horario.getHorarioBarbeiro().add(horarioBarbeiro);
                }
            }
        }
        barbeiro.setUpdatedAt(Instant.now());
        return BarbeiroAdapter.fromEntityToRegistroBarbeiro(this.barbeiroRepository.save(barbeiro));
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
        Barbeiro barbeiro = this.barbeiroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro de barbeiro nao existe"));
        barbeiro.setDeletedAt(null);
        return BarbeiroAdapter.fromEntityToRegistroBarbeiro(this.barbeiroRepository.save(barbeiro));
    }

    @Override
    public byte[] buscarImagemDoBarbeiro (Long idDoBarbeiro){
        Barbeiro barbeiroSelecionado = this.buscarBarbeiroPorId(idDoBarbeiro);
        return Base64.getDecoder().decode(barbeiroSelecionado.getImagem().getBase64Imagem());
    }

    @Override
    @Transactional
    public void atribuirImagemBarbeiro(MultipartFile file, Long idBarbeiro){
        Barbeiro barbeiroSelecionado = this.buscarBarbeiroPorId(idBarbeiro);
        barbeiroSelecionado
                .setImagem(new Imagem(null, this.transformarImagem(file)));
        this.barbeiroRepository.saveAndFlush(barbeiroSelecionado);
    }


    private Barbeiro buscarBarbeiroPorId(Long id){
        return this.barbeiroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro de barbeiro nao existe"));
    }

    private String transformarImagem(MultipartFile arquivoImagem){
        try{
            return Base64.getEncoder().encodeToString(arquivoImagem.getBytes());
        } catch (Exception exception){
            throw new RuntimeException(exception.getMessage());
        }
    }
}
