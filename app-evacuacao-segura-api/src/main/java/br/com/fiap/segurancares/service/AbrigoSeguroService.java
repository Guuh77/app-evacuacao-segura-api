package br.com.fiap.segurancares.service;

import br.com.fiap.segurancares.entity.AbrigoSeguroEntity;
import br.com.fiap.segurancares.exception.ResourceNotFoundException;
import br.com.fiap.segurancares.repository.AbrigoSeguroRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class AbrigoSeguroService {

    private final AbrigoSeguroRepository abrigoSeguroRepository;

    @Inject
    public AbrigoSeguroService(AbrigoSeguroRepository abrigoSeguroRepository) {
        this.abrigoSeguroRepository = abrigoSeguroRepository;
    }

    @Transactional
    public AbrigoSeguroEntity createAbrigoSeguro(AbrigoSeguroEntity abrigoSeguroEntity) {
        abrigoSeguroRepository.persist(abrigoSeguroEntity);
        return abrigoSeguroEntity;
    }

    public List<AbrigoSeguroEntity> findAllAbrigosSeguros(int page, int pageSize) {
        return abrigoSeguroRepository.findAll().page(page, pageSize).list();
    }

    public AbrigoSeguroEntity findAbrigoSeguroById(Long id) {
        return abrigoSeguroRepository.findByIdOptional(id)
                .orElseThrow(() -> new ResourceNotFoundException("Abrigo Seguro não encontrado com id: " + id));
    }

    @Transactional
    public AbrigoSeguroEntity updateAbrigoSeguro(Long id, AbrigoSeguroEntity abrigoSeguroAtualizado) {
        AbrigoSeguroEntity abrigoExistente = findAbrigoSeguroById(id);

        abrigoExistente.setNomeAbrigo(abrigoSeguroAtualizado.getNomeAbrigo());
        abrigoExistente.setEnderecoCompleto(abrigoSeguroAtualizado.getEnderecoCompleto());
        abrigoExistente.setLatitudeAbrigo(abrigoSeguroAtualizado.getLatitudeAbrigo());
        abrigoExistente.setLongitudeAbrigo(abrigoSeguroAtualizado.getLongitudeAbrigo());
        abrigoExistente.setCapacidadeMaximaPessoas(abrigoSeguroAtualizado.getCapacidadeMaximaPessoas());
        abrigoExistente.setVagasDisponiveisAtual(abrigoSeguroAtualizado.getVagasDisponiveisAtual());
        abrigoExistente.setRecursosOferecidos(abrigoSeguroAtualizado.getRecursosOferecidos());
        abrigoExistente.setContatoResponsavelAbrigo(abrigoSeguroAtualizado.getContatoResponsavelAbrigo());
        abrigoExistente.setTelefoneContatoAbrigo(abrigoSeguroAtualizado.getTelefoneContatoAbrigo());
        abrigoExistente.setStatusOperacional(abrigoSeguroAtualizado.getStatusOperacional());
        abrigoExistente.setDataAbertura(abrigoSeguroAtualizado.getDataAbertura());
        abrigoExistente.setObservacoesAdicionais(abrigoSeguroAtualizado.getObservacoesAdicionais());

        return abrigoExistente;
    }

    @Transactional
    public void deleteAbrigoSeguroById(Long id) {
        AbrigoSeguroEntity abrigo = findAbrigoSeguroById(id);
        abrigoSeguroRepository.delete(abrigo);
    }

}