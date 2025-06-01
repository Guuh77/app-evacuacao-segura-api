package br.com.fiap.segurancares.service;

import br.com.fiap.segurancares.entity.AlertaEntity;
import br.com.fiap.segurancares.entity.AreaDeRiscoEntity;
import br.com.fiap.segurancares.entity.UsuarioEntity;
import br.com.fiap.segurancares.exception.ResourceNotFoundException;
import br.com.fiap.segurancares.repository.AlertaRepository;
import br.com.fiap.segurancares.repository.AreaDeRiscoRepository;
import br.com.fiap.segurancares.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AlertaService {

    private final AlertaRepository alertaRepository;
    private final UsuarioRepository usuarioRepository;
    private final AreaDeRiscoRepository areaDeRiscoRepository;

    @Inject
    public AlertaService(AlertaRepository alertaRepository, UsuarioRepository usuarioRepository, AreaDeRiscoRepository areaDeRiscoRepository) {
        this.alertaRepository = alertaRepository;
        this.usuarioRepository = usuarioRepository;
        this.areaDeRiscoRepository = areaDeRiscoRepository;
    }

    @Transactional
    public AlertaEntity createAlerta(AlertaEntity alertaEntity) {
        if (alertaEntity.getEmitidoPorUsuario() != null && alertaEntity.getEmitidoPorUsuario().getIdUsuario() != null) {
            UsuarioEntity usuarioEmissor = usuarioRepository.findByIdOptional(alertaEntity.getEmitidoPorUsuario().getIdUsuario())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuário emissor não encontrado com id: " + alertaEntity.getEmitidoPorUsuario().getIdUsuario()));
            alertaEntity.setEmitidoPorUsuario(usuarioEmissor);
        } else {
            throw new IllegalArgumentException("ID do usuário emissor não pode ser nulo para criar alerta.");
        }

        if (alertaEntity.getAreaDeRiscoAssociada() != null && alertaEntity.getAreaDeRiscoAssociada().getIdAreaRisco() != null) {
            AreaDeRiscoEntity areaDeRisco = areaDeRiscoRepository.findByIdOptional(alertaEntity.getAreaDeRiscoAssociada().getIdAreaRisco())
                    .orElseThrow(() -> new ResourceNotFoundException("Área de Risco não encontrada com id: " + alertaEntity.getAreaDeRiscoAssociada().getIdAreaRisco()));
            alertaEntity.setAreaDeRiscoAssociada(areaDeRisco);
        }

        alertaRepository.persist(alertaEntity);
        return alertaEntity;
    }

    public List<AlertaEntity> findAllAlertas(int page, int pageSize) {
        return alertaRepository.findAll().page(page, pageSize).list();
    }

    public AlertaEntity findAlertaById(Long id) {
        return alertaRepository.findByIdOptional(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alerta não encontrado com id: " + id));
    }

    @Transactional
    public AlertaEntity updateAlerta(Long id, AlertaEntity alertaAtualizado) {
        AlertaEntity alertaExistente = findAlertaById(id);

        alertaExistente.setTitulo(alertaAtualizado.getTitulo());
        alertaExistente.setDescricaoCompleta(alertaAtualizado.getDescricaoCompleta());
        alertaExistente.setTipoEventoAlerta(alertaAtualizado.getTipoEventoAlerta());
        alertaExistente.setNivelSeveridadeAlerta(alertaAtualizado.getNivelSeveridadeAlerta());
        alertaExistente.setDataHoraExpiracao(alertaAtualizado.getDataHoraExpiracao());
        alertaExistente.setInstrucoesSeguranca(alertaAtualizado.getInstrucoesSeguranca());

        if (alertaAtualizado.getEmitidoPorUsuario() != null && alertaAtualizado.getEmitidoPorUsuario().getIdUsuario() != null) {
            if (alertaExistente.getEmitidoPorUsuario() == null ||
                    !alertaExistente.getEmitidoPorUsuario().getIdUsuario().equals(alertaAtualizado.getEmitidoPorUsuario().getIdUsuario())) {
                UsuarioEntity novoUsuarioEmissor = usuarioRepository.findByIdOptional(alertaAtualizado.getEmitidoPorUsuario().getIdUsuario())
                        .orElseThrow(() -> new ResourceNotFoundException("Novo usuário emissor não encontrado com id: " + alertaAtualizado.getEmitidoPorUsuario().getIdUsuario()));
                alertaExistente.setEmitidoPorUsuario(novoUsuarioEmissor);
            }
        }
        else {
            alertaExistente.setEmitidoPorUsuario(null);
        }


        if (alertaAtualizado.getAreaDeRiscoAssociada() != null && alertaAtualizado.getAreaDeRiscoAssociada().getIdAreaRisco() != null) {
            if (alertaExistente.getAreaDeRiscoAssociada() == null ||
                    !alertaExistente.getAreaDeRiscoAssociada().getIdAreaRisco().equals(alertaAtualizado.getAreaDeRiscoAssociada().getIdAreaRisco())) {
                AreaDeRiscoEntity novaAreaDeRisco = areaDeRiscoRepository.findByIdOptional(alertaAtualizado.getAreaDeRiscoAssociada().getIdAreaRisco())
                        .orElseThrow(() -> new ResourceNotFoundException("Nova Área de Risco não encontrada com id: " + alertaAtualizado.getAreaDeRiscoAssociada().getIdAreaRisco()));
                alertaExistente.setAreaDeRiscoAssociada(novaAreaDeRisco);
            }
        } else {
            alertaExistente.setAreaDeRiscoAssociada(null);
        }

        return alertaExistente;
    }

    @Transactional
    public void deleteAlertaById(Long id) {
        AlertaEntity alerta = findAlertaById(id);
        alertaRepository.delete(alerta);
    }
}