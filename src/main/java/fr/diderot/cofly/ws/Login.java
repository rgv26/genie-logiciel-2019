package fr.diderot.cofly.ws;

import fr.diderot.cofly.dao.DAOFactory;
import fr.diderot.cofly.dao.PilotDAO;
import fr.diderot.cofly.dao.UserDAO;
import fr.diderot.cofly.metier.Credentials;
import fr.diderot.cofly.metier.Pilot;
import fr.diderot.cofly.metier.User;
import fr.diderot.cofly.utils.PasswordUtils;
import fr.diderot.cofly.utils.Tuple;

import javax.security.auth.login.CredentialException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Path("/login")
public class Login {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user")
    public Response authenticateUser(Credentials credentials) {
        try {
            Tuple<String, User> user;
            UserDAO dao;
            String token;
            NewCookie tokenCookie, firstNameCookie;

            dao = DAOFactory.getUserDAO();
            user = verifyUser(credentials);

            token = issueToken(credentials.getEmail());
            user.value.setToken(token);
            if (!dao.update(UUID.fromString(user.key), user.value)) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }

            tokenCookie = new NewCookie("token", token, "/", "", "token", 10, false);
            firstNameCookie = new NewCookie("firstName", user.value.getFirstName(), "/", "", "firstName", 10,
                    false);

            return Response.ok().cookie(tokenCookie).cookie(firstNameCookie).build();
        } catch (Exception e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/pilot")
    public Response authenticatePilot(Credentials credentials) {
        try {
            Tuple<String, Pilot> pilot;
            PilotDAO dao;
            String token;
            NewCookie tokenCookie, firstNameCookie;

            
            pilot = verifyPilot(credentials);
            dao = DAOFactory.getPilotDAO();
            token = issueToken(credentials.getEmail());

            pilot.value.setToken(token);
            dao.update(UUID.fromString(pilot.key), pilot.value);

            tokenCookie = new NewCookie("token", token, "/", "", "token", 10, false);
            firstNameCookie = new NewCookie("firstName", pilot.value.getFirstName(), "/", "", "firstName", 10,
                    false);

            return Response.ok().cookie(tokenCookie).cookie(firstNameCookie).build();
        } catch (Exception e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/person")
    public Response authenticatePersonne(Credentials credentials) {
        Response p = authenticatePilot(credentials);
        Response u = authenticateUser(credentials);
        if (p==u)
            return Response.status(Response.Status.FORBIDDEN).build();
        if(p.getStatus()==403)
            return u;
        else
            return p;
    }

    private String issueToken(String username) {
        Random random = new SecureRandom();
        return new BigInteger(130, random).toString();
    }


    private Tuple<String, User> verifyUser(Credentials credentials) throws CredentialException {
        UserDAO dao = DAOFactory.getUserDAO();
        Tuple<String, User> user = dao.findByTagOneElement("email", credentials.getEmail());
        if (user == null
                || !(user.value.getPassword().equals(PasswordUtils.getSecurePassword(credentials.getPassword()))))
            throw new CredentialException("Wrong username or password");

        return user;
    }


    private Tuple<String, Pilot> verifyPilot(Credentials credentials) throws CredentialException {
        PilotDAO dao = DAOFactory.getPilotDAO();
        Tuple<String, Pilot> pilot = dao.findByTagOneElement("email", credentials.getEmail());

        if (pilot == null
                || !(pilot.value.getPassword().equals(PasswordUtils.getSecurePassword(credentials.getPassword()))))
            throw new CredentialException("Wrong username or password");

        return pilot;
    }

}
