package fr.diderot.cofly.ws;

import fr.diderot.cofly.metier.Flight;
import fr.diderot.cofly.utils.Tuple;
import fr.diderot.cofly.dao.DAOFactory;
import fr.diderot.cofly.dao.FlightDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@Path("/flights")
public class FlightWs {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tuple<String, Flight>> getAllAvaillibleFlights() {
        FlightDAO flightDAO = DAOFactory.getFlightDAO();
        return flightDAO.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Tuple<String, Flight> getAvaillibleFlights(@PathParam("id") String id) {
        FlightDAO flightDAO = DAOFactory.getFlightDAO();
        // TODO : Récupe l'id du pilot en connexion et de l'avion en parametre

        return flightDAO.find(UUID.fromString(id));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/search/{departure}/{arrival}")
    public List<Tuple<String, Flight>> searchFlights(@PathParam("departure") String departure,
            @PathParam("arrival") String arrival) {
        FlightDAO dao = new FlightDAO();
        List<Tuple<String, Flight>> rslt = dao.checkFly(departure, arrival);
        System.out.println("rslt = \n" + rslt.toString());
        return rslt;
    }

    /**
     * Done
     * 
     * @param flight
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addFlight(Flight flight) {
        // FlightDAO flightDao = DAOFactory.getFlightDAO();
        // Tuple<String, Flight> newFlight = new Tuple<>(flightDao.create(flight),
        // flight).;
        return Response.status(Response.Status.OK).entity(null).build();

    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response updateFlight(Flight flight, @PathParam("id") int id) {
        return Response.status(Response.Status.OK).entity("bien modifier le vol pour id:" + id).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteFlight(@PathParam("id") int id) {
        return Response.status(Response.Status.OK).entity("bien supprimer le vol pour id:" + id).build();
    }

}
