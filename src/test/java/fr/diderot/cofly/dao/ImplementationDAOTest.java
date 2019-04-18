package fr.diderot.cofly.dao;

import org.junit.Test;

import fr.diderot.cofly.metier.*;
import fr.diderot.cofly.utils.Airfields;
import fr.diderot.cofly.utils.PasswordUtils;
import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

public class ImplementationDAOTest<V> {

    @Test
    public void removeAll() {
        ImplementationDAO<V> dao = new ImplementationDAO<>();
        assertTrue(dao.removeAll());
    }

    @Test
    public void addAllAirfields() {
        AirfieldDAO dao = DAOFactory.getAirfieldDAO();
        Arrays.asList(Airfields.AIRFIELDS).forEach((String field) -> {
            Airfield obj = new Airfield(field);
            assertNotNull(dao.create(obj));
        });
    }
    @Test
    public void addAllIndex() {
        String idPilot, idUser, idAircraft, idFlight, idBooking;
        PilotDAO pilotDAO = DAOFactory.getPilotDAO();
        UserDAO userDao = DAOFactory.getUserDAO();
        AircraftDAO aircraftDao = DAOFactory.getAircraftDAO();
        FlightDAO flightDao = DAOFactory.getFlightDAO();
        BookingDAO bookingDao = DAOFactory.getBookingDAO();

        User user = new User("r.r@gmail.fr",
                PasswordUtils.getSecurePassword("12345"), "Pierre", "Dibo", 'M',
                "null", "null");
        Pilot pilot = new Pilot("yrcine.hamzacherif@gmail.com",
                PasswordUtils.getSecurePassword("12345678"), "Yacine",
                "Hamzacheri", 'M', "null", "null");
        Aircraft aircraft = new Aircraft("AFR3321", 250, "AT43", "null");


        assertNotNull(idPilot = pilotDAO.create(pilot));
        assertNotNull(idUser = userDao.create(user));
        assertNotNull(idAircraft = aircraftDao.create(aircraft));

        Flight flight = new Flight(idPilot, idAircraft, Airfields.AIRFIELDS[0], 
        Airfields.AIRFIELDS[0], LocalDateTime.now(), null, 0, 
        0, 0, Collections.emptyList());

        assertNotNull(idFlight = flightDao.create(flight));

        Booking booking = new Booking(user.getFirstName(), user.getLastName(),
                LocalDateTime.now(), idFlight, idUser, Booking.BookingState.WAITING, 0);

        assertNotNull(idBooking = bookingDao.create(booking));

        assertTrue(pilotDAO.removeById(UUID.fromString(idPilot)));
        assertTrue(userDao.removeById(UUID.fromString(idUser)));
        assertTrue(aircraftDao.removeById(UUID.fromString(idAircraft)));
        assertTrue(flightDao.removeById(UUID.fromString(idFlight)));
        assertTrue(bookingDao.removeById(UUID.fromString(idBooking)));
    }

    

    @Test
    public void addUser() {
        UserDAO dao = DAOFactory.getUserDAO();
        User obj = new User("a4leyxis@gmail.fr",
                PasswordUtils.getSecurePassword("12345"), "Gregory", "Sav", 'M',
                null, "0766811795");
        assertNotNull(dao.create(obj));
    }

    @Test
    public void removeUser() {
        UserDAO dao = DAOFactory.getUserDAO();
        String id = "5b018065-628d-4818-ac13-f56f17bca14f";
        assertTrue(dao.removeById(UUID.fromString(id)));
    }

}
