package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.AssistantDTO;
import facades.AssistantFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("assistant")
public class AssistantResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final AssistantFacade assistantFacade =  AssistantFacade.getAssistantFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"assistant endpoint\"}";
    }

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllAssistant() {
        List<AssistantDTO> assistantDTOS = assistantFacade.getAllAssistants();
        return Response.ok().entity(GSON.toJson(assistantDTOS)).build();
    }

    @POST
    @Path("/create")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createAssistant(String content){
        AssistantDTO assistantDTO = GSON.fromJson(content, AssistantDTO.class);
        AssistantDTO newAssistantDTO = assistantFacade.createAssistant(assistantDTO);
        return Response.ok().entity(GSON.toJson(newAssistantDTO)).build();
    }








}