package fr.diderot.cofly.dao;

import fr.diderot.cofly.metier.Pilot;
import fr.diderot.cofly.utils.Tuple;
import java.util.List;
import java.util.UUID;

public class PilotDAO extends ImplementationDAO<Pilot> {

    public PilotDAO() {
        super(Pilot.class);
    }

    @Override
    public boolean removeById(UUID id) {
        return super.removeById(id);
    }

    @Override
    public boolean update(UUID id, Pilot obj) {
        return super.update(id, obj);
    }

    @Override
    public boolean delete(Pilot obj) {
        return super.delete(obj);
    }

    @Override
    public List<Tuple<String, Pilot>> findAll() {
        return super.findAll();
    }

    @Override
    public Tuple<String, Pilot> find(UUID id) {
        return super.find(id);
    }

    @Override
    public String create(Pilot obj) {
        return super.create(obj);
    }
    
    

}
