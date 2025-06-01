package br.com.fiap.segurancares.controller; // Pacote correto

import br.com.fiap.segurancares.entity.AbrigoSeguroEntity;
import br.com.fiap.segurancares.service.AbrigoSeguroService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/abrigos-seguros")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AbrigoSeguroController {

    private final AbrigoSeguroService abrigoSeguroService;

    @Inject
    public AbrigoSeguroController(AbrigoSeguroService abrigoSeguroService) {
        this.abrigoSeguroService = abrigoSeguroService;
    }

    @POST
    @Transactional
    public Response createAbrigoSeguro(AbrigoSeguroEntity abrigoSeguroEntity) {
        AbrigoSeguroEntity novoAbrigo = abrigoSeguroService.createAbrigoSeguro(abrigoSeguroEntity);
        return Response.status(Response.Status.CREATED).entity(novoAbrigo).build();
    }

    @GET
    public Response findAllAbrigosSeguros(
            @QueryParam("page") @DefaultValue("0") Integer page,
            @QueryParam("pageSize") @DefaultValue("10") Integer pageSize) {
        List<AbrigoSeguroEntity> abrigos = abrigoSeguroService.findAllAbrigosSeguros(page, pageSize);
        return Response.ok(abrigos).build();
    }

    @GET
    @Path("/{id}")
    public Response findAbrigoSeguroById(@PathParam("id") Long id) {
        AbrigoSeguroEntity abrigo = abrigoSeguroService.findAbrigoSeguroById(id);
        return Response.ok(abrigo).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateAbrigoSeguro(@PathParam("id") Long id, AbrigoSeguroEntity abrigoSeguroEntity) {
        AbrigoSeguroEntity abrigoAtualizado = abrigoSeguroService.updateAbrigoSeguro(id, abrigoSeguroEntity);
        return Response.ok(abrigoAtualizado).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteAbrigoSeguroById(@PathParam("id") Long id) {
        abrigoSeguroService.deleteAbrigoSeguroById(id);
        return Response.noContent().build();
    }
}