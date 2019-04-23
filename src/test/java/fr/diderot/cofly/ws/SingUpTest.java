package fr.diderot.cofly.ws;

import fr.diderot.cofly.metier.Pilot;
import fr.diderot.cofly.metier.User;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class SingUpTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(SignUp.class);
    }

    @Test
    public void signUpUserFailGivenUserWithOnlyEmailAndPassword() {
        User user = new User();
        user.setEmail("yacine.hamzacherif@gmail.com");
        user.setPassword("yacineyacine");
        Response response = target("/signup/user").request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(user, MediaType.APPLICATION_JSON_TYPE));
        Assert.assertEquals("Http Response should be 400", 400, response.getStatus());
    }

    @Test
    public void signUpUserFailGivenUserWithPasswordLessThen8() {
        User user = new User();
        user.setEmail("yacine.hamzacherif@gmail.com");
        user.setPassword("yacineyacine");
        user.setFirstName("Yacine");
        user.setLastName("Hamza Cherif");

        Response response = target("/signup/user").request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(user, MediaType.APPLICATION_JSON_TYPE));

        Assert.assertEquals("Http Response should be 400", 400, response.getStatus());
    }

    @Test
    public void signUpUserSuccessWithValidateUser() {
        User user = new User();
        user.setEmail("yacine.hamzacherif@gmail.com");
        user.setPassword("yacineyacine");
        user.setFirstName("Yacine");
        user.setLastName("Hamza Cherif");
        System.out.println("user = " + user);

        Response response = target("/signup/user").request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(user, MediaType.APPLICATION_JSON_TYPE));

        Assert.assertEquals("Http Response should be 200", 200, response.getStatus());

    }

    @Test
    public void signUpPilotFailGivenUserWithOnlyEmailAndPassword() {
        Pilot pilot = new Pilot();
        pilot.setEmail("yacine.hamzacherif@gmail.com");
        pilot.setPassword("yacineyacine");

        Response response = target("/signup/pilot").request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(pilot, MediaType.APPLICATION_JSON_TYPE));

        Assert.assertEquals("Response status should be 400", 400, response.getStatus());
    }

    @Test
    public void signUpPilotFailGivenUserWithPasswordLessThen8() {
        Pilot pilot = new Pilot();
        pilot.setEmail("yacine.hamzacherif@gmail.com");
        pilot.setLastName("Hamza Cherif");
        pilot.setFirstName("Yacine");
        pilot.setPassword("yacine");

        Response response = target("/signup/pilot").request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(pilot, MediaType.APPLICATION_JSON_TYPE));

        Assert.assertEquals("Response status should be 400", 400, response.getStatus());

    }

    @Test
    public void signUpPilotSuccessWithValidateUser() {
        Pilot pilot = new Pilot();
        pilot.setEmail("yacine.hamzacherif@gmail.com");
        pilot.setPassword("yacineyacine");
        pilot.setFirstName("Yacine");
        pilot.setLastName("Hamza Cherif");

        Response response = target("/signup/pilot").request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(pilot, MediaType.APPLICATION_JSON_TYPE));

        Assert.assertEquals("Response status should be 200", 200, response.getStatus());
    }
}