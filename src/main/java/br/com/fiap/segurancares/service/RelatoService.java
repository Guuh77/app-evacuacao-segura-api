package br.com.fiap.segurancares.service;

import br.com.fiap.segurancares.entity.*;
import br.com.fiap.segurancares.exception.ResourceNotFoundException;
import br.com.fiap.segurancares.repository.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class RelatoService {

    private final RelatoRepository relatoRepository;
    private final UsuarioRepository usuarioRepository;
    private final OcorrenciaRepository ocorrenciaRepository;
    private final AlertaRepository alertaRepository;
    private final AreaDeRiscoRepository areaDeRiscoRepository;
    private final AbrigoSeguroRepository abrigoSeguroRepository;
    private final CampanhaRepository campanhaRepository;

    @Inject
    public RelatoService(RelatoRepository relatoRepository,
                         UsuarioRepository usuarioRepository,
                         OcorrenciaRepository ocorrenciaRepository,
                         AlertaRepository alertaRepository,
                         AreaDeRiscoRepository areaDeRiscoRepository,
                         AbrigoSeguroRepository abrigoSeguroRepository,
                         CampanhaRepository campanhaRepository) {
        this.relatoRepository = relatoRepository;
        this.usuarioRepository = usuarioRepository;
        this.ocorrenciaRepository = ocorrenciaRepository;
        this.alertaRepository = alertaRepository;
        this.areaDeRiscoRepository = areaDeRiscoRepository;
        this.abrigoSeguroRepository = abrigoSeguroRepository;
        this.campanhaRepository = campanhaRepository;
    }

    @Transactional
    public RelatoEntity createRelato(RelatoEntity relatoEntity) {
        if (relatoEntity.getUsuarioAutor() == null || relatoEntity.getUsuarioAutor().getIdUsuario() == null) {
            throw new IllegalArgumentException("Usuário autor é obrigatório para criar um relato.");
        }
        UsuarioEntity autor = usuarioRepository.findByIdOptional(relatoEntity.getUsuarioAutor().getIdUsuario())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário autor não encontrado com id: " + relatoEntity.getUsuarioAutor().getIdUsuario()));
        relatoEntity.setUsuarioAutor(autor);

        if (relatoEntity.getOcorrenciaAssociada() != null && relatoEntity.getOcorrenciaAssociada().getIdOcorrencia() != null) {
            OcorrenciaEntity ocorrencia = ocorrenciaRepository.findByIdOptional(relatoEntity.getOcorrenciaAssociada().getIdOcorrencia())
                    .orElseThrow(() -> new ResourceNotFoundException("Ocorrência associada não encontrada com id: " + relatoEntity.getOcorrenciaAssociada().getIdOcorrencia()));
            relatoEntity.setOcorrenciaAssociada(ocorrencia);
        }

        if (relatoEntity.getAlertaAssociado() != null && relatoEntity.getAlertaAssociado().getIdAlerta() != null) {
            AlertaEntity alerta = alertaRepository.findByIdOptional(relatoEntity.getAlertaAssociado().getIdAlerta())
                    .orElseThrow(() -> new ResourceNotFoundException("Alerta associado não encontrado com id: " + relatoEntity.getAlertaAssociado().getIdAlerta()));
            relatoEntity.setAlertaAssociado(alerta);
        }

        if (relatoEntity.getAreaRiscoAssociada() != null && relatoEntity.getAreaRiscoAssociada().getIdAreaRisco() != null) {
            AreaDeRiscoEntity area = areaDeRiscoRepository.findByIdOptional(relatoEntity.getAreaRiscoAssociada().getIdAreaRisco())
                    .orElseThrow(() -> new ResourceNotFoundException("Área de Risco associada não encontrada com id: " + relatoEntity.getAreaRiscoAssociada().getIdAreaRisco()));
            relatoEntity.setAreaRiscoAssociada(area);
        }

        if (relatoEntity.getAbrigoAssociado() != null && relatoEntity.getAbrigoAssociado().getIdAbrigo() != null) {
            AbrigoSeguroEntity abrigo = abrigoSeguroRepository.findByIdOptional(relatoEntity.getAbrigoAssociado().getIdAbrigo())
                    .orElseThrow(() -> new ResourceNotFoundException("Abrigo associado não encontrado com id: " + relatoEntity.getAbrigoAssociado().getIdAbrigo()));
            relatoEntity.setAbrigoAssociado(abrigo);
        }

        if (relatoEntity.getCampanhaAssociada() != null && relatoEntity.getCampanhaAssociada().getIdCampanha() != null) {
            CampanhaEntity campanha = campanhaRepository.findByIdOptional(relatoEntity.getCampanhaAssociada().getIdCampanha())
                    .orElseThrow(() -> new ResourceNotFoundException("Campanha associada não encontrada com id: " + relatoEntity.getCampanhaAssociada().getIdCampanha()));
            relatoEntity.setCampanhaAssociada(campanha);
        }

        relatoRepository.persist(relatoEntity);
        return relatoEntity;
    }

    public List<RelatoEntity> findAllRelatos(int page, int pageSize) {
        return relatoRepository.findAll().page(page, pageSize).list();
    }

    public RelatoEntity findRelatoById(Long id) {
        return relatoRepository.findByIdOptional(id)
                .orElseThrow(() -> new ResourceNotFoundException("Relato não encontrado com id: " + id));
    }

    @Transactional
    public RelatoEntity updateRelato(Long id, RelatoEntity relatoAtualizado) {
        RelatoEntity relatoExistente = findRelatoById(id);

        relatoExistente.setTituloRelato(relatoAtualizado.getTituloRelato());
        relatoExistente.setTextoRelato(relatoAtualizado.getTextoRelato());
        relatoExistente.setLatitudeRelato(relatoAtualizado.getLatitudeRelato());
        relatoExistente.setLongitudeRelato(relatoAtualizado.getLongitudeRelato());
        relatoExistente.setTipoRelato(relatoAtualizado.getTipoRelato());
        relatoExistente.setStatusValidacaoRelato(relatoAtualizado.getStatusValidacaoRelato());
        relatoExistente.setAnonimo(relatoAtualizado.isAnonimo());
        if (relatoAtualizado.getOcorrenciaAssociada() != null && relatoAtualizado.getOcorrenciaAssociada().getIdOcorrencia() != null) {
            OcorrenciaEntity ocorrencia = ocorrenciaRepository.findByIdOptional(relatoAtualizado.getOcorrenciaAssociada().getIdOcorrencia())
                    .orElseThrow(() -> new ResourceNotFoundException("Ocorrência associada não encontrada com id: " + relatoAtualizado.getOcorrenciaAssociada().getIdOcorrencia()));
            relatoExistente.setOcorrenciaAssociada(ocorrencia);
        }
        else {
            relatoExistente.setOcorrenciaAssociada(null);
        }


        if (relatoAtualizado.getAlertaAssociado() != null && relatoAtualizado.getAlertaAssociado().getIdAlerta() != null) {
            AlertaEntity alerta = alertaRepository.findByIdOptional(relatoAtualizado.getAlertaAssociado().getIdAlerta())
                    .orElseThrow(() -> new ResourceNotFoundException("Alerta associado não encontrado com id: " + relatoAtualizado.getAlertaAssociado().getIdAlerta()));
            relatoExistente.setAlertaAssociado(alerta);
        }
        else {
            relatoExistente.setAlertaAssociado(null);
        }

        if (relatoAtualizado.getAreaRiscoAssociada() != null && relatoAtualizado.getAreaRiscoAssociada().getIdAreaRisco() != null) {
            AreaDeRiscoEntity area = areaDeRiscoRepository.findByIdOptional(relatoAtualizado.getAreaRiscoAssociada().getIdAreaRisco())
                    .orElseThrow(() -> new ResourceNotFoundException("Área de Risco associada não encontrada com id: " + relatoAtualizado.getAreaRiscoAssociada().getIdAreaRisco()));
            relatoExistente.setAreaRiscoAssociada(area);
        }
        else {
            relatoExistente.setAreaRiscoAssociada(null);
        }

        if (relatoAtualizado.getAbrigoAssociado() != null && relatoAtualizado.getAbrigoAssociado().getIdAbrigo() != null) {
            AbrigoSeguroEntity abrigo = abrigoSeguroRepository.findByIdOptional(relatoAtualizado.getAbrigoAssociado().getIdAbrigo())
                    .orElseThrow(() -> new ResourceNotFoundException("Abrigo associado não encontrado com id: " + relatoAtualizado.getAbrigoAssociado().getIdAbrigo()));
            relatoExistente.setAbrigoAssociado(abrigo);
        }
        else {
            relatoExistente.setAbrigoAssociado(null);
        }

        if (relatoAtualizado.getCampanhaAssociada() != null && relatoAtualizado.getCampanhaAssociada().getIdCampanha() != null) {
            CampanhaEntity campanha = campanhaRepository.findByIdOptional(relatoAtualizado.getCampanhaAssociada().getIdCampanha())
                    .orElseThrow(() -> new ResourceNotFoundException("Campanha associada não encontrada com id: " + relatoAtualizado.getCampanhaAssociada().getIdCampanha()));
            relatoExistente.setCampanhaAssociada(campanha);
        }
        else {
            relatoExistente.setCampanhaAssociada(null);
        }

        return relatoExistente;
    }

    @Transactional
    public void deleteRelatoById(Long id) {
        RelatoEntity relato = findRelatoById(id);
        relatoRepository.delete(relato);
    }
}