package br.com.fiap.segurancares.repository;

import br.com.fiap.segurancares.entity.AbrigoSeguroEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class AbrigoSeguroRepository implements PanacheRepositoryBase<AbrigoSeguroEntity, Long> {
}