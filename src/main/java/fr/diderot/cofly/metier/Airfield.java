package fr.diderot.cofly.metier;

import java.util.Objects;
import java.util.UUID;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement(name = "airfield")
public class Airfield {

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
    private UUID id;
    private String name;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Airfield() {

    }

    public Airfield(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.name);
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
        final Airfield other = (Airfield) obj;
        return Objects.equals(this.name, other.name);
    }

    @Override
    public String toString() {
        return "Airfield{" + "name=" + name + '}';
    }

}
