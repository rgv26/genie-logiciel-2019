package fr.diderot.cofly.ws;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import javax.ws.rs.core.Application;

public class LoginTest extends JerseyTest {
    @Override
    protected Application configure() {
        return new ResourceConfig(Login.class);
    }

}