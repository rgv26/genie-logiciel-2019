package fr.diderot.cofly.metier;

import fr.diderot.cofly.customserializer.LocalDateTimeDeserializer;
import fr.diderot.cofly.customserializer.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement(name = "flight")
public class Flight {

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    private UUID id;
    private String idPilot;
    private String idAircraft;
    private String departure;
    private String arrival;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime date;
    private String appointenmtAdress;
    private double price;
    private int seats;
    private double flightDuration;
    private List<String> idBookings;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getIdPilot() {
        return idPilot;
    }

    public void setIdPilot(String idPilot) {
        this.idPilot = idPilot;
    }

    public String getIdAircraft() {
        return idAircraft;
    }

    public void setIdAircraft(String idAircraft) {
        this.idAircraft = idAircraft;
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

    public List<String> getIdBookings() {
        return idBookings;
    }

    public void setIdBookings(List<String> idBookings) {
        this.idBookings = idBookings;
    }

    public Flight() {

    }

    public Flight(String idPilot, String idAircraft, String departure,
            String arrival, LocalDateTime date, String appointenmtAdress,
            double price, int seats, double flightDuration) {
        this.idPilot = idPilot;
        this.idAircraft = idAircraft;
        this.departure = departure;
        this.arrival = arrival;
        this.date = date;
        this.appointenmtAdress = appointenmtAdress;
        this.price = price;
        this.seats = seats;
        this.flightDuration = flightDuration;
        this.idBookings = new ArrayList<>();
    }

    public Flight(String idPilot, String idAircraft, String departure,
            String arrival, LocalDateTime date, String appointenmtAdress,
            double price, int seats, double flightDuration,
            List<String> idBookings) {
        this.idPilot = idPilot;
        this.idAircraft = idAircraft;
        this.departure = departure;
        this.arrival = arrival;
        this.date = date;
        this.appointenmtAdress = appointenmtAdress;
        this.price = price;
        this.seats = seats;
        this.flightDuration = flightDuration;
        this.idBookings = idBookings;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.idPilot);
        hash = 53 * hash + Objects.hashCode(this.idAircraft);
        hash = 53 * hash + Objects.hashCode(this.departure);
        hash = 53 * hash + Objects.hashCode(this.arrival);
        hash = 53 * hash + Objects.hashCode(this.date);
        hash = 53 * hash + Objects.hashCode(this.appointenmtAdress);
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.price)
                ^ (Double.doubleToLongBits(this.price) >>> 32));
        hash = 53 * hash + this.seats;
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.flightDuration)
                ^ (Double.doubleToLongBits(this.flightDuration) >>> 32));
        hash = 53 * hash + Objects.hashCode(this.idBookings);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Flight other = (Flight) obj;
        if (Double.doubleToLongBits(this.price)
                != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if (this.seats != other.seats) {
            return false;
        }
        if (Double.doubleToLongBits(this.flightDuration)
                != Double.doubleToLongBits(other.flightDuration)) {
            return false;
        }
        if (!Objects.equals(this.idPilot, other.idPilot)) {
            return false;
        }
        if (!Objects.equals(this.idAircraft, other.idAircraft)) {
            return false;
        }
        if (!Objects.equals(this.departure, other.departure)) {
            return false;
        }
        if (!Objects.equals(this.arrival, other.arrival)) {
            return false;
        }
        if (!Objects.equals(this.appointenmtAdress, other.appointenmtAdress)) {
            return false;
        }

        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return Objects.equals(this.idBookings, other.idBookings);
    }

    @Override
    public String toString() {
        return "Flight{" + "idPilot=" + idPilot + ", idAircraft=" + idAircraft
                + ", departure=" + departure + ", arrival=" + arrival
                + ", date=" + date + ", appointenmtAdress=" + appointenmtAdress
                + ", price=" + price + ", seats=" + seats
                + ", flightDuration=" + flightDuration
                + ", idBookings=" + idBookings + '}';
    }

}
