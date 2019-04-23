package fr.diderot.cofly.dao;

public class DAOFactory {

    private static AirfieldDAO airfieldDAO = null;
    private static AircraftDAO aircraftDAO = null;
    private static BookingDAO bookingDAO = null;
    private static FlightDAO flightDAO = null;
    private static PilotDAO pilotDAO = null;
    private static UserDAO userDAO = null;


    public static AirfieldDAO getAirfieldDAO() {
        if (airfieldDAO == null) {
            airfieldDAO = new AirfieldDAO();
        }
        return airfieldDAO;
    }

    public static AircraftDAO getAircraftDAO() {
        if (aircraftDAO == null) {
            aircraftDAO = new AircraftDAO();
        }
        return aircraftDAO;
    }

    public static BookingDAO getBookingDAO() {
        if (bookingDAO == null) {
            bookingDAO = new BookingDAO();
        }
        return bookingDAO;
    }

    public static FlightDAO getFlightDAO() {
        if (flightDAO == null) {
            flightDAO = new FlightDAO();
        }
        return flightDAO;
    }

    public static PilotDAO getPilotDAO() {
        if (pilotDAO == null) {
            pilotDAO = new PilotDAO();
        }
        return pilotDAO;
    }

    public static UserDAO getUserDAO() {
        if (userDAO == null) {
            userDAO = new UserDAO();
        }
        return userDAO;
    }

}
