package br.com.fiap.segurancares.controller;

import br.com.fiap.segurancares.entity.UsuarioEntity;
import br.com.fiap.segurancares.service.UsuarioService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Inject
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @POST
    @Transactional
    public Response createUsuario(UsuarioEntity usuarioEntity) {
        UsuarioEntity novoUsuario = usuarioService.createUsuario(usuarioEntity);
        return Response.status(Response.Status.CREATED).entity(novoUsuario).build();
    }

    @GET
    public Response findAllUsuarios(
            @QueryParam("page") @DefaultValue("0") Integer page,
            @QueryParam("pageSize") @DefaultValue("10") Integer pageSize) {
        List<UsuarioEntity> usuarios = usuarioService.findAllUsuarios(page, pageSize);
        return Response.ok(usuarios).build();
    }

    @GET
    @Path("/{id}")
    public Response findUsuarioById(@PathParam("id") Long id) {
        UsuarioEntity usuario = usuarioService.findUsuarioById(id);
        return Response.ok(usuario).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateUsuario(@PathParam("id") Long id, UsuarioEntity usuarioEntity) {
        UsuarioEntity usuarioAtualizado = usuarioService.updateUsuario(id, usuarioEntity);
        return Response.ok(usuarioAtualizado).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteUsuarioById(@PathParam("id") Long id) {
        usuarioService.deleteUsuarioById(id);
        return Response.noContent().build();
    }
}