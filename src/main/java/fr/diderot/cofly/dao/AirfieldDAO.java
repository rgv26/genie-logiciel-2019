package fr.diderot.cofly.dao;

import fr.diderot.cofly.metier.Airfield;
import fr.diderot.cofly.utils.Tuple;
import java.util.List;
import java.util.UUID;

public class AirfieldDAO extends ImplementationDAO<Airfield> {

    public AirfieldDAO() {
        super(Airfield.class);
    }

    @Override
    public boolean removeById(UUID id) {
        return super.removeById(id);
    }

    @Override
    public boolean update(UUID id, Airfield obj) {
        return super.update(id, obj);
    }

    @Override
    public boolean delete(Airfield obj) {
        return super.delete(obj);
    }

    @Override
    public List<Tuple<String, Airfield>> findAll() {
        return super.findAll();
    }

    @Override
    public Tuple<String, Airfield> find(UUID id) {
        return super.find(id);
    }

    @Override
    public String create(Airfield obj) {
        return super.create(obj);
    }

}
