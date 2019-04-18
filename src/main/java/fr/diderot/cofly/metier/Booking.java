package fr.diderot.cofly.metier;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement(name = "booking")
public class Booking {

    public enum BookingState {
        WAITING, CONFIRMED, DECLINE
    }

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    private UUID id;
    private String firstName;
    private String lastName;
    private LocalDateTime date;
    private String idFlight;
    private String reservedBy;
    private BookingState state;
    private int seats;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getIdFlight() {
        return idFlight;
    }

    public void setIdFlight(String idFlight) {
        this.idFlight = idFlight;
    }

    public String getReservedBy() {
        return reservedBy;
    }

    public void setReservedBy(String reservedBy) {
        this.reservedBy = reservedBy;
    }

    public BookingState getState() {
        return state;
    }

    public void setState(BookingState state) {
        this.state = state;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public Booking() {

    }

    public Booking(String firstName, String lastName, LocalDateTime date,
            String idFlight, String reservedBy, BookingState state, int seats) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.date = date;
        this.idFlight = idFlight;
        this.reservedBy = reservedBy;
        this.state = state;
        this.seats = seats;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.firstName);
        hash = 53 * hash + Objects.hashCode(this.lastName);
        hash = 53 * hash + Objects.hashCode(this.date);
        hash = 53 * hash + Objects.hashCode(this.idFlight);
        hash = 53 * hash + Objects.hashCode(this.reservedBy);
        hash = 53 * hash + Objects.hashCode(this.state);
        hash = 53 * hash + this.seats;
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
        final Booking other = (Booking) obj;
        if (this.seats != other.seats) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.idFlight, other.idFlight)) {
            return false;
        }
        if (!Objects.equals(this.reservedBy, other.reservedBy)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return this.state == other.state;
    }

    @Override
    public String toString() {
        return "Booking{" + "firstName=" + firstName + ", lastName=" + lastName
                + ", date=" + date + ", idFlight=" + idFlight
                + ", reservedBy=" + reservedBy + ", state=" + state
                + ", seats=" + seats + '}';
    }

}
