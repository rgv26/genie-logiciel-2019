package fr.diderot.cofly.ws;

import fr.diderot.cofly.metier.Booking;
import fr.diderot.cofly.metier.Flight;
import fr.diderot.cofly.utils.BookFlightInformations;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Path("/bookings")
public class BookingWs {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/flight/{id}/")
    public Flight flightBookings(@PathParam("id") String id) {
        Flight flight = new Flight();
        /*
         * flight.setId(id); flight.setDeparture("Paris"); flight.setArrival("Paris");
         * flight.setDate(LocalDateTime.now().plusDays(2)); flight.setSeats(10);
         * flight.setPrice(50); Pilot pilot = new Pilot(); pilot.setFirstName("John");
         * pilot.setLastName("Doe"); // TODO : Récupe l'id du pilot en connexion //
         * flight.setPilot();
         * 
         * flight.getBookings().add(new Booking("Anis", "Hellal", LocalDateTime.now()));
         * flight.getBookings().add(new Booking("Sergio", "Aguero",
         * LocalDateTime.now())); flight.getBookings().add(new Booking("Paul", "Pogba",
         * LocalDateTime.now()));
         */

        return flight;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user/{id}")
    public List<Booking> userBooking(@QueryParam("id") String id) {
        // TODO : userBooking
        return Collections.emptyList();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/flight/{id}")
    public Response bookFlight(BookFlightInformations bookingInformation) {
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/{bookingId}/flight/{flightId}")
    public Response cancelBook(@PathParam("bookingId") String idBook, @PathParam("flightId") String idFlight) {
        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @Path("/bookings/{bookingId}/flight/{flightId}/confirm")
    public Response confirmBook(@PathParam("bookingId") String bookingId, @PathParam("flightId") String flightId) {
        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @Path("/bookings/{bookingId}/flight/{flightId}/decline")
    public Response declineBook(@PathParam("bookingId") String bookingId, @PathParam("flightId") String flightId) {
        return Response.status(Response.Status.OK).build();
    }
}
