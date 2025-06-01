package br.com.fiap.segurancares.controller;

import br.com.fiap.segurancares.entity.AlertaEntity;
import br.com.fiap.segurancares.service.AlertaService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/alertas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlertaController {

    private final AlertaService alertaService;

    @Inject
    public AlertaController(AlertaService alertaService) {
        this.alertaService = alertaService;
    }

    @POST
    @Transactional
    public Response createAlerta(AlertaEntity alertaEntity) {
        AlertaEntity novoAlerta = alertaService.createAlerta(alertaEntity);
        return Response.status(Response.Status.CREATED).entity(novoAlerta).build();
    }

    @GET
    public Response findAllAlertas(
            @QueryParam("page") @DefaultValue("0") Integer page,
            @QueryParam("pageSize") @DefaultValue("10") Integer pageSize) {
        List<AlertaEntity> alertas = alertaService.findAllAlertas(page, pageSize);
        return Response.ok(alertas).build();
    }

    @GET
    @Path("/{id}")
    public Response findAlertaById(@PathParam("id") Long id) {
        AlertaEntity alerta = alertaService.findAlertaById(id);
        return Response.ok(alerta).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateAlerta(@PathParam("id") Long id, AlertaEntity alertaEntity) {
        AlertaEntity alertaAtualizado = alertaService.updateAlerta(id, alertaEntity);
        return Response.ok(alertaAtualizado).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteAlertaById(@PathParam("id") Long id) {
        alertaService.deleteAlertaById(id);
        return Response.noContent().build();
    }
}