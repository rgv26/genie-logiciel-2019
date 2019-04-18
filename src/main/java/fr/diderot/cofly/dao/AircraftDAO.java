package fr.diderot.cofly.dao;

import fr.diderot.cofly.metier.Aircraft;
import fr.diderot.cofly.utils.Tuple;
import java.util.List;
import java.util.UUID;

public class AircraftDAO extends ImplementationDAO<Aircraft> {

    public AircraftDAO() {
        super(Aircraft.class);
    }

    @Override
    public boolean removeById(UUID id) {
        return super.removeById(id);
    }

    @Override
    public boolean update(UUID id, Aircraft obj) {
        return super.update(id, obj);
    }

    @Override
    public boolean delete(Aircraft obj) {
        return super.delete(obj);
    }

    @Override
    public List<Tuple<String, Aircraft>> findAll() {
        return super.findAll();
    }

    @Override
    public Tuple<String, Aircraft> find(UUID id) {
        return super.find(id);
    }

    @Override
    public String create(Aircraft obj) {
        return super.create(obj);
    }
    
}
