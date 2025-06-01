package br.com.fiap.segurancares.controller;

import br.com.fiap.segurancares.entity.RelatoEntity;
import br.com.fiap.segurancares.service.RelatoService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/relatos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RelatoController {

    private final RelatoService relatoService;

    @Inject
    public RelatoController(RelatoService relatoService) {
        this.relatoService = relatoService;
    }

    @POST
    @Transactional
    public Response createRelato(RelatoEntity relatoEntity) {
        RelatoEntity novoRelato = relatoService.createRelato(relatoEntity);
        return Response.status(Response.Status.CREATED).entity(novoRelato).build();
    }

    @GET
    public Response findAllRelatos(
            @QueryParam("page") @DefaultValue("0") Integer page,
            @QueryParam("pageSize") @DefaultValue("10") Integer pageSize) {
        List<RelatoEntity> relatos = relatoService.findAllRelatos(page, pageSize);
        return Response.ok(relatos).build();
    }

    @GET
    @Path("/{id}")
    public Response findRelatoById(@PathParam("id") Long id) {
        RelatoEntity relato = relatoService.findRelatoById(id);
        return Response.ok(relato).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateRelato(@PathParam("id") Long id, RelatoEntity relatoEntity) {
        RelatoEntity relatoAtualizado = relatoService.updateRelato(id, relatoEntity);
        return Response.ok(relatoAtualizado).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteRelatoById(@PathParam("id") Long id) {
        relatoService.deleteRelatoById(id);
        return Response.noContent().build();
    }
}