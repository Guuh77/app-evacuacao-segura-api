package br.com.fiap.segurancares.service;

import br.com.fiap.segurancares.entity.UsuarioEntity;
import br.com.fiap.segurancares.exception.ResourceNotFoundException;
import br.com.fiap.segurancares.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Inject
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public UsuarioEntity createUsuario(UsuarioEntity usuarioEntity) {
        usuarioRepository.persist(usuarioEntity);
        return usuarioEntity;
    }

    public List<UsuarioEntity> findAllUsuarios(int page, int pageSize) {
        return usuarioRepository.findAll()
                .page(page, pageSize)
                .list();
    }

    public UsuarioEntity findUsuarioById(Long id) {
        return usuarioRepository.findByIdOptional(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id: " + id));
    }

    @Transactional
    public UsuarioEntity updateUsuario(Long id, UsuarioEntity usuarioAtualizado) {
        UsuarioEntity usuarioExistente = findUsuarioById(id);

        usuarioExistente.setNomeCompleto(usuarioAtualizado.getNomeCompleto());
        usuarioExistente.setEmail(usuarioAtualizado.getEmail());
        usuarioExistente.setTelefone(usuarioAtualizado.getTelefone());
        usuarioExistente.setTipoUsuario(usuarioAtualizado.getTipoUsuario());
        usuarioExistente.setAtivo(usuarioAtualizado.isAtivo());
        if (usuarioAtualizado.getSenhaHash() != null && !usuarioAtualizado.getSenhaHash().isEmpty()) {
            usuarioExistente.setSenhaHash(usuarioAtualizado.getSenhaHash());
        }


        return usuarioExistente;
    }

    @Transactional
    public void deleteUsuarioById(Long id) {
        UsuarioEntity usuario = findUsuarioById(id);
        usuarioRepository.delete(usuario);
    }
}