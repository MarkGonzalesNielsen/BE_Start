package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.IngredientDTO;
import facades.IngredientFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("ingredient")
public class IngredientResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final IngredientFacade ingredientFacade =  IngredientFacade.getIngredientFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"recipe endpoint\"}";
    }

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllIngredients() {
        List<IngredientDTO> ingredients = ingredientFacade.getAllIngredients();
        return Response.ok().entity(GSON.toJson(ingredients)).build();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getIngredientById(@PathParam("id") Long id) {
        IngredientDTO ingredient = ingredientFacade.getIngredientById(id);
        return Response.ok().entity(GSON.toJson(ingredient)).build();
    }

    @POST
    @Path("/create")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createIngredient(String content){
        IngredientDTO ingredientDTO = GSON.fromJson(content, IngredientDTO.class);
        IngredientDTO newIngredientDTO = ingredientFacade.createIngredient(ingredientDTO);
        return Response.ok().entity(GSON.toJson(newIngredientDTO)).build();
    }

    @DELETE
    @Path("delete/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
   // @RolesAllowed("admin")
    public Response deleteIngredient(@PathParam("id") int id) {
        IngredientDTO deleted = ingredientFacade.deleteIngredient(id);
        return Response.ok().entity(GSON.toJson(deleted)).build();
    }

    @PUT
    @Path("/update")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    //@RolesAllowed("admin")
    public Response updateRentalInfo(String content){
        IngredientDTO ingredientDTO = GSON.fromJson(content, IngredientDTO.class);
        IngredientDTO newIngredientDTO = ingredientFacade.updateIngredient(ingredientDTO);
        return Response.ok().entity(GSON.toJson(newIngredientDTO)).build();
    }


}