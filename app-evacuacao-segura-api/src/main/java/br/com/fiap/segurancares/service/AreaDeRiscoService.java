package br.com.fiap.segurancares.service;

import br.com.fiap.segurancares.entity.AreaDeRiscoEntity;
import br.com.fiap.segurancares.exception.ResourceNotFoundException;
import br.com.fiap.segurancares.repository.AreaDeRiscoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class AreaDeRiscoService {

    private final AreaDeRiscoRepository areaDeRiscoRepository;

    @Inject
    public AreaDeRiscoService(AreaDeRiscoRepository areaDeRiscoRepository) {
        this.areaDeRiscoRepository = areaDeRiscoRepository;
    }

    @Transactional
    public AreaDeRiscoEntity createAreaDeRisco(AreaDeRiscoEntity areaDeRiscoEntity) {
        areaDeRiscoRepository.persist(areaDeRiscoEntity);
        return areaDeRiscoEntity;
    }

    public List<AreaDeRiscoEntity> findAllAreasDeRisco(int page, int pageSize) {
        return areaDeRiscoRepository.findAll().page(page, pageSize).list();
    }

    public AreaDeRiscoEntity findAreaDeRiscoById(Long id) {
        return areaDeRiscoRepository.findByIdOptional(id)
                .orElseThrow(() -> new ResourceNotFoundException("Área de Risco não encontrada com id: " + id));
    }

    @Transactional
    public AreaDeRiscoEntity updateAreaDeRisco(Long id, AreaDeRiscoEntity areaDeRiscoAtualizada) {
        AreaDeRiscoEntity areaExistente = findAreaDeRiscoById(id);

        areaExistente.setNomeArea(areaDeRiscoAtualizada.getNomeArea());
        areaExistente.setDescricaoRisco(areaDeRiscoAtualizada.getDescricaoRisco());
        areaExistente.setTipoRisco(areaDeRiscoAtualizada.getTipoRisco());
        areaExistente.setLatitudeCentro(areaDeRiscoAtualizada.getLatitudeCentro());
        areaExistente.setLongitudeCentro(areaDeRiscoAtualizada.getLongitudeCentro());
        areaExistente.setRaioKm(areaDeRiscoAtualizada.getRaioKm());
        areaExistente.setPoligonoCoordenadas(areaDeRiscoAtualizada.getPoligonoCoordenadas());
        areaExistente.setNivelRiscoPermanente(areaDeRiscoAtualizada.getNivelRiscoPermanente());

        return areaExistente;
    }

    @Transactional
    public void deleteAreaDeRiscoById(Long id) {
        AreaDeRiscoEntity area = findAreaDeRiscoById(id);
        areaDeRiscoRepository.delete(area);
    }

}