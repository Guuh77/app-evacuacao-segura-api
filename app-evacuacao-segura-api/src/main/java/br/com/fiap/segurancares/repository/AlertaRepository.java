package br.com.fiap.segurancares.repository;

import br.com.fiap.segurancares.entity.AlertaEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AlertaRepository implements PanacheRepositoryBase<AlertaEntity, Long> {
}