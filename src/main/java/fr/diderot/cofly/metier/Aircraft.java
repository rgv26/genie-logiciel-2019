package fr.diderot.cofly.metier;

import java.util.Objects;
import java.util.UUID;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement(name = "aircraft")
public class Aircraft {

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    private UUID id;
    private String reference;
    private int seats;
    private String model;
    private String photo;

    public Aircraft() {

    }

    public Aircraft(String reference, int seats, String model, String photo) {
        this.reference = reference;
        this.seats = seats;
        this.model = model;
        this.photo = photo;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.reference);
        hash = 53 * hash + this.seats;
        hash = 53 * hash + Objects.hashCode(this.model);
        hash = 53 * hash + Objects.hashCode(this.photo);
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
        final Aircraft other = (Aircraft) obj;
        if (this.seats != other.seats) {
            return false;
        }
        if (!Objects.equals(this.reference, other.reference)) {
            return false;
        }
        if (!Objects.equals(this.model, other.model)) {
            return false;
        }
        return Objects.equals(this.photo, other.photo);
    }

    @Override
    public String toString() {
        return "Aircraft{" + "reference=" + reference + ", seats=" + seats + ", model=" + model + ", photo=" + photo + '}';
    }

}
