package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.BookingDTO;
import facades.BookingFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("booking")
public class BookingResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final BookingFacade bookingFacade =  BookingFacade.getBookingFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"booking endpoint\"}";
    }

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllBookings() {
        List<BookingDTO> bookingDTOS = bookingFacade.getAllBookings();
        return Response.ok().entity(GSON.toJson(bookingDTOS)).build();
    }

    @DELETE
    @Path("delete/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
   // @RolesAllowed("admin")
    public Response deleteIngredient(@PathParam("id") int id) {
        BookingDTO bookingDTO = bookingFacade.deleteBooking(id);
        return Response.ok().entity(GSON.toJson(bookingDTO)).build();
    }



}