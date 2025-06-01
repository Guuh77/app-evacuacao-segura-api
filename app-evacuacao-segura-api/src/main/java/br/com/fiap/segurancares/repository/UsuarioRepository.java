package br.com.fiap.segurancares.repository;

import br.com.fiap.segurancares.entity.UsuarioEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepositoryBase<UsuarioEntity, Long> {
    }