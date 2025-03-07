package agendamento.servico.service.impl;

import agendamento.servico.adapter.BarbeiroAdapter;
import agendamento.servico.dto.AtualizarBarbeiro;
import agendamento.servico.dto.CadastroBarbeiro;
import agendamento.servico.dto.RegistroBarbeiro;
import agendamento.servico.entity.Barbeiro;
import agendamento.servico.repository.BarbeiroRepository;
import agendamento.servico.service.BarbeiroService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BarbeiroServiceImpl implements BarbeiroService {

    private BarbeiroRepository barbeiroRespository;

    @Override
    public RegistroBarbeiro cadastrarBarbeiro(CadastroBarbeiro dados) {
        Barbeiro barbeiro = BarbeiroAdapter.fromCadastroBarbeiroToEntity(dados);
        this.barbeiroRespository.save(barbeiro);
        return BarbeiroAdapter.fromEntityToRegistroBarbeiro(barbeiro);
    }

    @Override
    public List<RegistroBarbeiro> listarBarbeiros() {
        return this.barbeiroRespository.findByDeleteAtIsNull().stream().map(BarbeiroAdapter::fromEntityToRegistroBarbeiro).toList();
    }

    @Override
    public RegistroBarbeiro buscarBarbeiro(Long id) {
        Optional<Barbeiro> barbeiro = this.barbeiroRespository.findById(id);
        if (barbeiro.isEmpty() || barbeiro.get().getDeleteAt() != null) {
            throw new RuntimeException("Registro de barbeiro nao existe");
        }else{
            return BarbeiroAdapter.fromEntityToRegistroBarbeiro(barbeiro.get());
        }
    }

    @Override
    public RegistroBarbeiro atualizarBarbeiro(AtualizarBarbeiro dados) {
        Optional<Barbeiro> barbeiro = this.barbeiroRespository.findById(dados.id());
        if (barbeiro.isEmpty() || barbeiro.get().getDeleteAt() != null) {
            throw new RuntimeException("Registro de barbeiro nao existe");
        } else {
            if (dados.nome() != null && !dados.nome().isBlank()){
                barbeiro.get().setNome(dados.nome());
            }
            if (dados.caminhoImagem() != null && !dados.caminhoImagem().isBlank()){
                barbeiro.get().setCaminhoImagem(dados.caminhoImagem());
            }
            barbeiro.get().setUpdateAt(Instant.now());
            return BarbeiroAdapter.fromEntityToRegistroBarbeiro(this.barbeiroRespository.save(barbeiro.get()));
        }
    }

    @Override
    public void desativarBarbeiro(Long id) {
        Optional<Barbeiro> barbeiro = this.barbeiroRespository.findById(id);
        if (barbeiro.isEmpty() || barbeiro.get().getDeleteAt() != null){
            throw new RuntimeException("Registro de barbeiro nao existe");
        } else {
            barbeiro.get().setDeleteAt(Instant.now());
        }
    }

    @Override
    public RegistroBarbeiro ativarBarbeiro(Long id) {
        Optional<Barbeiro> barbeiro = this.barbeiroRespository.findById(id);
        if (barbeiro.isEmpty()){
            throw new RuntimeException("Registro de barbeiro nao existe");
        } else {
            barbeiro.get().setDeleteAt(null);
            return BarbeiroAdapter.fromEntityToRegistroBarbeiro(this.barbeiroRespository.save(barbeiro.get()));
        }
    }


}
