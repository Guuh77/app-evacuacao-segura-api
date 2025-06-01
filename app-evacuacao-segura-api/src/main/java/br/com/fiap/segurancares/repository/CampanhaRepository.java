package br.com.fiap.segurancares.repository; // Pacote correto

import br.com.fiap.segurancares.entity.CampanhaEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CampanhaRepository implements PanacheRepositoryBase<CampanhaEntity, Long> {
}