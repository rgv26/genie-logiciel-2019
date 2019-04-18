package fr.diderot.cofly.database;

import fr.diderot.cofly.metier.Airfield;
import fr.diderot.cofly.utils.Airfields;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.*;
import org.junit.Test;

public class SearchTest {

    @Test
    public void cleanBase() {
        try {
            Search.getAllIndex().forEach(t -> {
                try {
                    Search.remove(t);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void addAllAirfields() {
        Arrays.asList(Airfields.AIRFIELDS).forEach((String field) -> {
            Airfield airfield = new Airfield(field);
            try {
                assertNotNull(Search.add(airfield, Airfield.class.getSimpleName().toLowerCase()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    @Test
    public void findAllAirfields() {
        try {
            Search.find(Airfield.class.getSimpleName().toLowerCase(), Airfield.class)
            .forEach(System.out::println);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        assertTrue(true);
    }
}
