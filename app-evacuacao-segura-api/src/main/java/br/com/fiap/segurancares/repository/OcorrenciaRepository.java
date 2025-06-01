package br.com.fiap.segurancares.repository; // Pacote correto

import br.com.fiap.segurancares.entity.OcorrenciaEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class OcorrenciaRepository implements PanacheRepositoryBase<OcorrenciaEntity, Long> {
}