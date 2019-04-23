package fr.diderot.cofly.ws;

import fr.diderot.cofly.metier.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserWs {

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response upDateUser(User user, @PathParam("id") String id) {
        return Response.status(Response.Status.OK).entity("bien modifier" + user.getFirstName()).build();
    }
}
