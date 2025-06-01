package br.com.fiap.segurancares.repository; // Pacote correto

import br.com.fiap.segurancares.entity.RelatoEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RelatoRepository implements PanacheRepositoryBase<RelatoEntity, Long> {
}