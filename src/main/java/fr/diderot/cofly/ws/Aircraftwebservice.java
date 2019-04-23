package fr.diderot.cofly.ws;

import fr.diderot.cofly.dao.AircraftDAO;
import fr.diderot.cofly.dao.DAOFactory;
import fr.diderot.cofly.metier.Aircraft;
import fr.diderot.cofly.utils.Tuple;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;

@Path("/Aircraftwebservice")
public class Aircraftwebservice {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    // @Path("/aircraft")
    public List<Tuple<String, Aircraft>> getAllAircrafts() {
        AircraftDAO dao = DAOFactory.getAircraftDAO();
        return dao.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Tuple<String, Aircraft> getAircraft(@PathParam("id") String id) {
        AircraftDAO dao = DAOFactory.getAircraftDAO();

        return dao.find(UUID.fromString(id));
    }
}
