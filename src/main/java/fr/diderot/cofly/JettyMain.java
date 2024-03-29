package fr.diderot.cofly;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletContainer;

public class JettyMain {

    public static void main(String[] args) throws Exception {
        // Initialize the server
        Server server = new Server();

        // Add a connector
        ServerConnector connector = new ServerConnector(server);
        connector.setHost("0.0.0.0");
        connector.setPort(8081);
        connector.setIdleTimeout(30000);
        server.addConnector(connector);

        // Configure Jersey
        ResourceConfig rc = new ResourceConfig();
        rc.packages(true, "fr.diderot.cofly.ws");
        rc.register(JacksonFeature.class);
        rc.register(LoggingFilter.class);
        rc.property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        // Add a servlet handler for web services (/ws/*)
        ServletHolder servletHolder = new ServletHolder(new ServletContainer(rc));
        ServletContextHandler handlerWebServices = new ServletContextHandler(ServletContextHandler.SESSIONS);
        handlerWebServices.setContextPath("/ws");
        handlerWebServices.addServlet(servletHolder, "/*");

        // Add a handler for resources (/*)
        ResourceHandler handlerPortal = new ResourceHandler();
        handlerPortal.setResourceBase("src/main/webapp/Flight");
        handlerPortal.setDirectoriesListed(false);
        handlerPortal.setWelcomeFiles(new String[] { "HomePage.html" });

        ContextHandler handlerPortalCtx = new ContextHandler();
        handlerPortalCtx.setContextPath("/");
        handlerPortalCtx.setHandler(handlerPortal);

        // Activate handlers
        ContextHandlerCollection contexts = new ContextHandlerCollection();
        contexts.setHandlers(new Handler[] { handlerWebServices, handlerPortalCtx });
        server.setHandler(contexts);

        // Start server
        server.start();
    }
}