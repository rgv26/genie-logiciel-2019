package fr.diderot.cofly.dao;

import org.junit.Test;

import fr.diderot.cofly.metier.*;
import fr.diderot.cofly.utils.Airfields;
import fr.diderot.cofly.utils.PasswordUtils;
import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.Arrays;
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
                PasswordUtils.getSecurePassword("12345"), "Pierre",
                "Dibo", null, 'M', "null", "null");
        Pilot pilot = new Pilot("yrcine.hamzacherif@gmail.com",
                PasswordUtils.getSecurePassword("12345678"), "Yacine",
                "Hamzacheri",  'M', "null", "null");
        Aircraft aircraft = new Aircraft("AFR3321", 250, "AT43", "null");


        assertNotNull(idPilot = pilotDAO.create(pilot));
        assertNotNull(idUser = userDao.create(user));
        assertNotNull(idAircraft = aircraftDao.create(aircraft));

        Flight flight = new Flight(idPilot, idAircraft, Airfields.AIRFIELDS[0], 
        Airfields.AIRFIELDS[0], LocalDateTime.now(), null, 0.0,
        0, 0, "");

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
                PasswordUtils.getSecurePassword("12345"),
                "Gregory", "Sav", null, 'M',
                null, "0766811795");
        assertNotNull(dao.create(obj));
    }

    @Test
    public void addPilot() {
        PilotDAO dao = DAOFactory.getPilotDAO();
        Pilot obj = new Pilot("a@gmail.fr",
                PasswordUtils.getSecurePassword("12345"), "Gregory", "Sav", 'M',
                null, "0766811795");
        assertNotNull(dao.create(obj));
    }

    @Test
    public void addAircraft() {
        AircraftDAO dao = DAOFactory.getAircraftDAO();
        Aircraft obj = new Aircraft("AF104", 3, "AF", null);
        assertNotNull(dao.create(obj));
    }

    @Test
    public void addFlight() {
        FlightDAO dao = DAOFactory.getFlightDAO();
        String id = "98aec889-cbae-4d32-ba0b-eb74db1bb95c";
        Flight obj = new Flight(id, "AF104", Airfields.AIRFIELDS[0], 
        Airfields.AIRFIELDS[0], LocalDateTime.now(), null, 50, 5, 1, null);
        assertNotNull(dao.create(obj));
    }

    @Test
    public void removeUser() {
        UserDAO dao = DAOFactory.getUserDAO();
        String id = "a68b9901-dc2c-47d6-be42-37835ac18517";
        assertTrue(dao.removeById(UUID.fromString(id)));
    }

    @Test
    public void removePilot() {
        PilotDAO dao = DAOFactory.getPilotDAO();
        String id = "e40e942a-9008-4d06-b26e-8ef2d54038cb";
        assertTrue(dao.removeById(UUID.fromString(id)));
    }

}
