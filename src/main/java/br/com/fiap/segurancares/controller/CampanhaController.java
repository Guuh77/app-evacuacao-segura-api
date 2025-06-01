package br.com.fiap.segurancares.controller;

import br.com.fiap.segurancares.entity.CampanhaEntity;
import br.com.fiap.segurancares.service.CampanhaService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/campanhas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CampanhaController {

    private final CampanhaService campanhaService;

    @Inject
    public CampanhaController(CampanhaService campanhaService) {
        this.campanhaService = campanhaService;
    }

    @POST
    @Transactional
    public Response createCampanha(CampanhaEntity campanhaEntity) {
        CampanhaEntity novaCampanha = campanhaService.createCampanha(campanhaEntity);
        return Response.status(Response.Status.CREATED).entity(novaCampanha).build();
    }

    @GET
    public Response findAllCampanhas(
            @QueryParam("page") @DefaultValue("0") Integer page,
            @QueryParam("pageSize") @DefaultValue("10") Integer pageSize) {
        List<CampanhaEntity> campanhas = campanhaService.findAllCampanhas(page, pageSize);
        return Response.ok(campanhas).build();
    }

    @GET
    @Path("/{id}")
    public Response findCampanhaById(@PathParam("id") Long id) {
        CampanhaEntity campanha = campanhaService.findCampanhaById(id);
        return Response.ok(campanha).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateCampanha(@PathParam("id") Long id, CampanhaEntity campanhaEntity) {
        CampanhaEntity campanhaAtualizada = campanhaService.updateCampanha(id, campanhaEntity);
        return Response.ok(campanhaAtualizada).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteCampanhaById(@PathParam("id") Long id) {
        campanhaService.deleteCampanhaById(id);
        return Response.noContent().build();
    }
}