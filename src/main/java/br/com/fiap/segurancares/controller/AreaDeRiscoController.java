package br.com.fiap.segurancares.controller;

import br.com.fiap.segurancares.entity.AreaDeRiscoEntity;
import br.com.fiap.segurancares.service.AreaDeRiscoService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/areas-de-risco")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AreaDeRiscoController {

    private final AreaDeRiscoService areaDeRiscoService;

    @Inject
    public AreaDeRiscoController(AreaDeRiscoService areaDeRiscoService) {
        this.areaDeRiscoService = areaDeRiscoService;
    }

    @POST
    @Transactional
    public Response createAreaDeRisco(AreaDeRiscoEntity areaDeRiscoEntity) {
        AreaDeRiscoEntity novaArea = areaDeRiscoService.createAreaDeRisco(areaDeRiscoEntity);
        return Response.status(Response.Status.CREATED).entity(novaArea).build();
    }

    @GET
    public Response findAllAreasDeRisco(
            @QueryParam("page") @DefaultValue("0") Integer page,
            @QueryParam("pageSize") @DefaultValue("10") Integer pageSize) {
        List<AreaDeRiscoEntity> areas = areaDeRiscoService.findAllAreasDeRisco(page, pageSize);
        return Response.ok(areas).build();
    }

    @GET
    @Path("/{id}")
    public Response findAreaDeRiscoById(@PathParam("id") Long id) {
        AreaDeRiscoEntity area = areaDeRiscoService.findAreaDeRiscoById(id);
        return Response.ok(area).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateAreaDeRisco(@PathParam("id") Long id, AreaDeRiscoEntity areaDeRiscoEntity) {
        AreaDeRiscoEntity areaAtualizada = areaDeRiscoService.updateAreaDeRisco(id, areaDeRiscoEntity);
        return Response.ok(areaAtualizada).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteAreaDeRiscoById(@PathParam("id") Long id) {
        areaDeRiscoService.deleteAreaDeRiscoById(id);
        return Response.noContent().build();
    }
}