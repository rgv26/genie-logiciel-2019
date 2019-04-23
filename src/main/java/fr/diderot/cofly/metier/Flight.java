package fr.diderot.cofly.metier;

import fr.diderot.cofly.customserializer.LocalDateTimeDeserializer;
import fr.diderot.cofly.customserializer.LocalDateTimeSerializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Flight {

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    private long id;
    private String departure;
    private String arrival;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime date;
    private String appointenmtAdress;
    private String idAircraft;
    private String idPilot;
    private double price;
    private int seats;
    private String image;
    private List<Booking> bookings;
    private double flightDuration;

    public String getIdAircraft() {
        return this.idAircraft;
    }

    public String getIdPilot() {
        return this.idPilot;
    }

    public void setIdAircraft(String id) {
        this.idAircraft = id;
    }

    public void setIdPilot(String id) {
        this.idPilot = id;
    }

    public Flight(String idPilot, String idAircraft, String departure, String arrival, LocalDateTime date, String appointenmtAdress, double price, int seats, double flightDuration, String image) {
        this.departure = departure;
        this.arrival = arrival;
        this.date = date;
        this.appointenmtAdress = appointenmtAdress;
        this.idAircraft = idAircraft;
        this.idPilot = idPilot;
        this.price = price;
        this.seats = seats;
        this.flightDuration = flightDuration;
    }

    public Flight() {
        bookings = new ArrayList<>();
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getAppointenmtAdress() {
        return appointenmtAdress;
    }

    public void setAppointenmtAdress(String appointenmtAdress) {
        this.appointenmtAdress = appointenmtAdress;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public double getFlightDuration() {
        return flightDuration;
    }

    public void setFlightDuration(double flightDuration) {
        this.flightDuration = flightDuration;
    }

    @Override
    public String toString() {
        return "Flight{" + ", departure='" + departure + '\'' + ", arrival='" + arrival + '\'' + ", date="
                + date + ", appointenmtAdress='" + appointenmtAdress + '\'' + ", idAircraft=" + idAircraft
                + ", idPilot=" + idPilot + ", price=" + price + ", seats=" + seats + ", image=" + "null" + ", bookings="
                + bookings + '}';
    }
}
