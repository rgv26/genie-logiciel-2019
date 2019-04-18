package fr.diderot.cofly.dao;

import fr.diderot.cofly.metier.Booking;
import fr.diderot.cofly.utils.Tuple;
import java.util.List;
import java.util.UUID;

public class BookingDAO extends ImplementationDAO<Booking> {

    public BookingDAO() {
        super(Booking.class);
    }

    @Override
    public boolean removeById(UUID id) {
        return super.removeById(id);
    }

    @Override
    public boolean update(UUID id, Booking obj) {
        return super.update(id, obj);
    }

    @Override
    public boolean delete(Booking obj) {
        return super.delete(obj);
    }

    @Override
    public List<Tuple<String, Booking>> findAll() {
        return super.findAll();
    }

    @Override
    public Tuple<String, Booking> find(UUID id) {
        return super.find(id);
    }

    @Override
    public String create(Booking obj) {
        return super.create(obj);
    }   

}
