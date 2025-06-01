package br.com.fiap.segurancares.service;

import br.com.fiap.segurancares.entity.AlertaEntity;
import br.com.fiap.segurancares.entity.AreaDeRiscoEntity;
import br.com.fiap.segurancares.entity.OcorrenciaEntity;
import br.com.fiap.segurancares.entity.UsuarioEntity;
import br.com.fiap.segurancares.exception.ResourceNotFoundException;
import br.com.fiap.segurancares.repository.AlertaRepository;
import br.com.fiap.segurancares.repository.AreaDeRiscoRepository;
import br.com.fiap.segurancares.repository.OcorrenciaRepository;
import br.com.fiap.segurancares.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class OcorrenciaService {

    private final OcorrenciaRepository ocorrenciaRepository;
    private final UsuarioRepository usuarioRepository;
    private final AlertaRepository alertaRepository;
    private final AreaDeRiscoRepository areaDeRiscoRepository;

    @Inject
    public OcorrenciaService(OcorrenciaRepository ocorrenciaRepository,
                             UsuarioRepository usuarioRepository,
                             AlertaRepository alertaRepository,
                             AreaDeRiscoRepository areaDeRiscoRepository) {
        this.ocorrenciaRepository = ocorrenciaRepository;
        this.usuarioRepository = usuarioRepository;
        this.alertaRepository = alertaRepository;
        this.areaDeRiscoRepository = areaDeRiscoRepository;
    }

    @Transactional
    public OcorrenciaEntity createOcorrencia(OcorrenciaEntity ocorrenciaEntity) {
        if (ocorrenciaEntity.getUsuarioReportou() != null && ocorrenciaEntity.getUsuarioReportou().getIdUsuario() != null) {
            UsuarioEntity usuario = usuarioRepository.findByIdOptional(ocorrenciaEntity.getUsuarioReportou().getIdUsuario())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuário (reportou) não encontrado com id: " + ocorrenciaEntity.getUsuarioReportou().getIdUsuario()));
            ocorrenciaEntity.setUsuarioReportou(usuario);
        }

        if (ocorrenciaEntity.getAlertaRelacionado() != null && ocorrenciaEntity.getAlertaRelacionado().getIdAlerta() != null) {
            AlertaEntity alerta = alertaRepository.findByIdOptional(ocorrenciaEntity.getAlertaRelacionado().getIdAlerta())
                    .orElseThrow(() -> new ResourceNotFoundException("Alerta relacionado não encontrado com id: " + ocorrenciaEntity.getAlertaRelacionado().getIdAlerta()));
            ocorrenciaEntity.setAlertaRelacionado(alerta);
        }

        if (ocorrenciaEntity.getAreaRiscoAfetada() != null && ocorrenciaEntity.getAreaRiscoAfetada().getIdAreaRisco() != null) {
            AreaDeRiscoEntity area = areaDeRiscoRepository.findByIdOptional(ocorrenciaEntity.getAreaRiscoAfetada().getIdAreaRisco())
                    .orElseThrow(() -> new ResourceNotFoundException("Área de Risco afetada não encontrada com id: " + ocorrenciaEntity.getAreaRiscoAfetada().getIdAreaRisco()));
            ocorrenciaEntity.setAreaRiscoAfetada(area);
        }

        ocorrenciaRepository.persist(ocorrenciaEntity);
        return ocorrenciaEntity;
    }

    public List<OcorrenciaEntity> findAllOcorrencias(int page, int pageSize) {
        return ocorrenciaRepository.findAll().page(page, pageSize).list();
    }

    public OcorrenciaEntity findOcorrenciaById(Long id) {
        return ocorrenciaRepository.findByIdOptional(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ocorrência não encontrada com id: " + id));
    }

    @Transactional
    public OcorrenciaEntity updateOcorrencia(Long id, OcorrenciaEntity ocorrenciaAtualizada) {
        OcorrenciaEntity ocorrenciaExistente = findOcorrenciaById(id);

        ocorrenciaExistente.setTituloOcorrencia(ocorrenciaAtualizada.getTituloOcorrencia());
        ocorrenciaExistente.setDescricaoDetalhada(ocorrenciaAtualizada.getDescricaoDetalhada());
        ocorrenciaExistente.setTipoOcorrencia(ocorrenciaAtualizada.getTipoOcorrencia());
        ocorrenciaExistente.setLatitudeOcorrencia(ocorrenciaAtualizada.getLatitudeOcorrencia());
        ocorrenciaExistente.setLongitudeOcorrencia(ocorrenciaAtualizada.getLongitudeOcorrencia());
        ocorrenciaExistente.setDataHoraOcorrencia(ocorrenciaAtualizada.getDataHoraOcorrencia());
        ocorrenciaExistente.setStatusOcorrencia(ocorrenciaAtualizada.getStatusOcorrencia());
        ocorrenciaExistente.setImpactoEstimado(ocorrenciaAtualizada.getImpactoEstimado());

        if (ocorrenciaAtualizada.getUsuarioReportou() != null && ocorrenciaAtualizada.getUsuarioReportou().getIdUsuario() != null) {
            UsuarioEntity usuario = usuarioRepository.findByIdOptional(ocorrenciaAtualizada.getUsuarioReportou().getIdUsuario())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuário (reportou) não encontrado com id: " + ocorrenciaAtualizada.getUsuarioReportou().getIdUsuario()));
            ocorrenciaExistente.setUsuarioReportou(usuario);
        } else {
            ocorrenciaExistente.setUsuarioReportou(null);
        }

        if (ocorrenciaAtualizada.getAlertaRelacionado() != null && ocorrenciaAtualizada.getAlertaRelacionado().getIdAlerta() != null) {
            AlertaEntity alerta = alertaRepository.findByIdOptional(ocorrenciaAtualizada.getAlertaRelacionado().getIdAlerta())
                    .orElseThrow(() -> new ResourceNotFoundException("Alerta relacionado não encontrado com id: " + ocorrenciaAtualizada.getAlertaRelacionado().getIdAlerta()));
            ocorrenciaExistente.setAlertaRelacionado(alerta);
        }
        else {
            ocorrenciaExistente.setAlertaRelacionado(null);
        }

        if (ocorrenciaAtualizada.getAreaRiscoAfetada() != null && ocorrenciaAtualizada.getAreaRiscoAfetada().getIdAreaRisco() != null) {
            AreaDeRiscoEntity area = areaDeRiscoRepository.findByIdOptional(ocorrenciaAtualizada.getAreaRiscoAfetada().getIdAreaRisco())
                    .orElseThrow(() -> new ResourceNotFoundException("Área de Risco afetada não encontrada com id: " + ocorrenciaAtualizada.getAreaRiscoAfetada().getIdAreaRisco()));
            ocorrenciaExistente.setAreaRiscoAfetada(area);
        } else {
            ocorrenciaExistente.setAreaRiscoAfetada(null);
        }
        return ocorrenciaExistente;
    }

    @Transactional
    public void deleteOcorrenciaById(Long id) {
        OcorrenciaEntity ocorrencia = findOcorrenciaById(id);
        ocorrenciaRepository.delete(ocorrencia);
    }
}