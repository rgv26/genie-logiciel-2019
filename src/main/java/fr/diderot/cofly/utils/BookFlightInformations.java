package fr.diderot.cofly.utils;

import fr.diderot.cofly.metier.Person;

import java.util.ArrayList;
import java.util.List;

public class BookFlightInformations {

    private String message;
    private List<Person> passengers;

    public BookFlightInformations() {
        this.passengers = new ArrayList<>();
    }

    public BookFlightInformations(String message) {
        this();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Person> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Person> passengers) {
        this.passengers = passengers;
    }


    public boolean addPassenger(Person passenger) {
        return passengers.add(passenger);
    }

    public boolean removePassenger(Person passenger) {
        return passengers.remove(passenger);
    }

}
