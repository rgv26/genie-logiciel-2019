package fr.diderot.cofly.dao;

import fr.diderot.cofly.database.Search;
import fr.diderot.cofly.metier.Flight;
import fr.diderot.cofly.utils.Tuple;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class FlightDAO extends ImplementationDAO<Flight> {

    public FlightDAO() {
        super(Flight.class);
    }

    @Override
    public boolean removeById(UUID id) {
        return super.removeById(id);
    }

    @Override
    public boolean update(UUID id, Flight obj) {
        return super.update(id, obj);
    }

    @Override
    public boolean delete(Flight obj) {
        return super.delete(obj);
    }

    @Override
    public List<Tuple<String, Flight>> findAll() {
        return super.findAll();
    }

    @Override
    public Tuple<String, Flight> find(UUID id) {
        return super.find(id);
    }

    @Override
    public String create(Flight obj) {
        return super.create(obj);
    }

    public List<Tuple<String, Flight>> checkFly(String departure, String arrival) {
        try {
            return Search.searchAllFlightWithOutDate(departure, arrival, tClass);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
