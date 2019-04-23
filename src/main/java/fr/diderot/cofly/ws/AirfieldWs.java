package fr.diderot.cofly.ws;

import fr.diderot.cofly.dao.AirfieldDAO;
import fr.diderot.cofly.dao.DAOFactory;
import fr.diderot.cofly.metier.Airfield;
import fr.diderot.cofly.utils.Tuple;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.LinkedList;
import java.util.List;

@Path("/airport")
public class AirfieldWs {

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public List<String> allAirport() {
        List<String> name = new LinkedList<>();
        List<Tuple<String, Airfield>> data;
        AirfieldDAO dao = DAOFactory.getAirfieldDAO();

        data = dao.findAll();

        if (data != null) {
            for (int i = 0; i < data.size(); i++) {
                name.add(data.get(i).value.getName());
            }
        }

        return name;
    }
}
