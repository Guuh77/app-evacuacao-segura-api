package br.com.fiap.segurancares.service;

import br.com.fiap.segurancares.entity.CampanhaEntity;
import br.com.fiap.segurancares.entity.UsuarioEntity;
import br.com.fiap.segurancares.exception.ResourceNotFoundException;
import br.com.fiap.segurancares.repository.CampanhaRepository;
import br.com.fiap.segurancares.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class CampanhaService {

    private final CampanhaRepository campanhaRepository;
    private final UsuarioRepository usuarioRepository;

    @Inject
    public CampanhaService(CampanhaRepository campanhaRepository, UsuarioRepository usuarioRepository) {
        this.campanhaRepository = campanhaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public CampanhaEntity createCampanha(CampanhaEntity campanhaEntity) {
        if (campanhaEntity.getOrganizadorUsuario() != null && campanhaEntity.getOrganizadorUsuario().getIdUsuario() != null) {
            UsuarioEntity organizador = usuarioRepository.findByIdOptional(campanhaEntity.getOrganizadorUsuario().getIdUsuario())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuário organizador não encontrado com id: " + campanhaEntity.getOrganizadorUsuario().getIdUsuario()));
            campanhaEntity.setOrganizadorUsuario(organizador);
        }
        campanhaRepository.persist(campanhaEntity);
        return campanhaEntity;
    }

    public List<CampanhaEntity> findAllCampanhas(int page, int pageSize) {
        return campanhaRepository.findAll().page(page, pageSize).list();
    }

    public CampanhaEntity findCampanhaById(Long id) {
        return campanhaRepository.findByIdOptional(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campanha não encontrada com id: " + id));
    }

    @Transactional
    public CampanhaEntity updateCampanha(Long id, CampanhaEntity campanhaAtualizada) {
        CampanhaEntity campanhaExistente = findCampanhaById(id);

        campanhaExistente.setNomeCampanha(campanhaAtualizada.getNomeCampanha());
        campanhaExistente.setDescricaoCampanha(campanhaAtualizada.getDescricaoCampanha());
        campanhaExistente.setTipoCampanha(campanhaAtualizada.getTipoCampanha());
        campanhaExistente.setDataInicioCampanha(campanhaAtualizada.getDataInicioCampanha());
        campanhaExistente.setDataFimCampanha(campanhaAtualizada.getDataFimCampanha());
        campanhaExistente.setPublicoAlvo(campanhaAtualizada.getPublicoAlvo());
        campanhaExistente.setEntidadeOrganizadora(campanhaAtualizada.getEntidadeOrganizadora());
        campanhaExistente.setStatusCampanha(campanhaAtualizada.getStatusCampanha());
        campanhaExistente.setMetaCampanha(campanhaAtualizada.getMetaCampanha());
        campanhaExistente.setLocalPrincipalCampanha(campanhaAtualizada.getLocalPrincipalCampanha());

        if (campanhaAtualizada.getOrganizadorUsuario() != null && campanhaAtualizada.getOrganizadorUsuario().getIdUsuario() != null) {
            UsuarioEntity organizador = usuarioRepository.findByIdOptional(campanhaAtualizada.getOrganizadorUsuario().getIdUsuario())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuário organizador não encontrado com id: " + campanhaAtualizada.getOrganizadorUsuario().getIdUsuario()));
            campanhaExistente.setOrganizadorUsuario(organizador);
        }
        else {
            campanhaExistente.setOrganizadorUsuario(null);
        }

        return campanhaExistente;
    }

    @Transactional
    public void deleteCampanhaById(Long id) {
        CampanhaEntity campanha = findCampanhaById(id);
        campanhaRepository.delete(campanha);
    }
}