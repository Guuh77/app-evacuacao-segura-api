package br.com.fiap.segurancares.controller;

import br.com.fiap.segurancares.entity.OcorrenciaEntity;
import br.com.fiap.segurancares.service.OcorrenciaService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/ocorrencias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OcorrenciaController {

    private final OcorrenciaService ocorrenciaService;

    @Inject
    public OcorrenciaController(OcorrenciaService ocorrenciaService) {
        this.ocorrenciaService = ocorrenciaService;
    }

    @POST
    @Transactional
    public Response createOcorrencia(OcorrenciaEntity ocorrenciaEntity) {
        OcorrenciaEntity novaOcorrencia = ocorrenciaService.createOcorrencia(ocorrenciaEntity);
        return Response.status(Response.Status.CREATED).entity(novaOcorrencia).build();
    }

    @GET
    public Response findAllOcorrencias(
            @QueryParam("page") @DefaultValue("0") Integer page,
            @QueryParam("pageSize") @DefaultValue("10") Integer pageSize) {
        List<OcorrenciaEntity> ocorrencias = ocorrenciaService.findAllOcorrencias(page, pageSize);
        return Response.ok(ocorrencias).build();
    }

    @GET
    @Path("/{id}")
    public Response findOcorrenciaById(@PathParam("id") Long id) {
        OcorrenciaEntity ocorrencia = ocorrenciaService.findOcorrenciaById(id);
        return Response.ok(ocorrencia).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateOcorrencia(@PathParam("id") Long id, OcorrenciaEntity ocorrenciaEntity) {
        OcorrenciaEntity ocorrenciaAtualizada = ocorrenciaService.updateOcorrencia(id, ocorrenciaEntity);
        return Response.ok(ocorrenciaAtualizada).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteOcorrenciaById(@PathParam("id") Long id) {
        ocorrenciaService.deleteOcorrenciaById(id);
        return Response.noContent().build();
    }
}