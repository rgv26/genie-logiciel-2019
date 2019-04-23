package fr.diderot.cofly.ws;

import fr.diderot.cofly.dao.DAOFactory;
import fr.diderot.cofly.dao.PilotDAO;
import fr.diderot.cofly.dao.UserDAO;
import fr.diderot.cofly.metier.Pilot;
import fr.diderot.cofly.metier.User;
import fr.diderot.cofly.utils.PasswordUtils;
import fr.diderot.cofly.utils.Tuple;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;

@Path("/signup")
public class SignUp {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/user")
    public Response signUpUser(@Valid User user) {
        UserDAO dao = DAOFactory.getUserDAO();
        user.setPassword(PasswordUtils.getSecurePassword(new String(user.getPassword())));
        dao.create(user);
        return Response.status(Response.Status.OK).entity("success" + user).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/pilot")
    public Response signUpPilot(@Valid Pilot pilot) throws NoSuchAlgorithmException {
        pilot.setPassword(PasswordUtils.getSecurePassword(new String(pilot.getPassword())));
        PilotDAO dao = DAOFactory.getPilotDAO();
        dao.create(pilot);
        return Response.status(Response.Status.OK).entity("bien ajouter" + pilot).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/email/{email}")
    public boolean checkEmail(@PathParam("email") String email) {
        UserDAO daoUser = DAOFactory.getUserDAO();
        PilotDAO daoPilot = DAOFactory.getPilotDAO();

        return daoUser.findSimpleQueries("email", email).isEmpty() 
            || daoPilot.findSimpleQueries("email", email).isEmpty();
    }
}
