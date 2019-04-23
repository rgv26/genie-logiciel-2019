package fr.diderot.cofly.ws;

import fr.diderot.cofly.dao.DAOFactory;
import fr.diderot.cofly.dao.FlightDAO;
import fr.diderot.cofly.metier.Flight;
import fr.diderot.cofly.metier.Pilot;
import fr.diderot.cofly.utils.Tuple;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/pilots")
public class PilotWs {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pilot> getAllPilots() {
        ArrayList<Pilot> pilots = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Pilot pilot = new Pilot();
            pilots.add(pilot);
        }
        return pilots;
    }

    @GET
    @Path("{id}/flights")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tuple<String, Flight>> getFlightsByPilot(@PathParam("id") String id) {
        FlightDAO flight = DAOFactory.getFlightDAO();
        // TODO : Récupe l'id du pilot en connexion et de l'avion en parametre
        return flight.findAll();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePilote(Pilot pilot, @PathParam("id") String id) {
        return Response.status(Response.Status.OK).entity("bien modifier" + pilot.getFirstName()).build();
    }
}
