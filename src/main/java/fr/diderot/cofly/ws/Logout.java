package fr.diderot.cofly.ws;

import fr.diderot.cofly.dao.DAOFactory;
import fr.diderot.cofly.dao.PilotDAO;
import fr.diderot.cofly.dao.UserDAO;
import fr.diderot.cofly.metier.Pilot;
import fr.diderot.cofly.metier.User;
import fr.diderot.cofly.utils.Tuple;

import java.util.List;
import java.util.UUID;

import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

@Path("/logout")
public class Logout {

    @GET
    @Path("/p")
    public Response logout(@CookieParam("token") Cookie token) {

        System.out.println("ICI");
        //Response p = logoutPilot(token);
        //Response u = logoutUser(token);

        return null;
    }
 

    @GET
    @Path("/pilot")
    public Response logoutPilot(Cookie token) {
        PilotDAO dao;
        Tuple<String, Pilot> pilot;

        System.out.println("token pilot = " + token);

        return Response.noContent().build();
        //@Context HttpHeaders httpHeaders
        //System.out.println("httpHeaders.getCookies() = " + httpHeaders.getCookies());
        /*dao = DAOFactory.getPilotDAO();
        pilot = dao.findByTagOneElement("token", token);

        if (pilot != null){
            pilot.value.setToken(null);
            dao.update(UUID.fromString(pilot.key), pilot.value);
            System.out.println(pilot);
        }

        NewCookie tokenCookie = new NewCookie("token", null, "/", "", "token", 0, false);
        NewCookie firstNameCookie = new NewCookie("firstName", null, "/", "", "firstName", 0, false);

        return Response.ok().cookie(tokenCookie).cookie(firstNameCookie).build();*/
    }

}
