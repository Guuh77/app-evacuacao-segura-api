package br.com.fiap.segurancares.repository;

import br.com.fiap.segurancares.entity.AreaDeRiscoEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AreaDeRiscoRepository implements PanacheRepositoryBase<AreaDeRiscoEntity, Long> {
}