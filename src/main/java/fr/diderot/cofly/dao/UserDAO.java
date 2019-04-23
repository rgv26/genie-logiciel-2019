package fr.diderot.cofly.dao;

import fr.diderot.cofly.database.Search;
import fr.diderot.cofly.metier.User;
import fr.diderot.cofly.utils.Tuple;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class UserDAO extends ImplementationDAO<User> {

    public UserDAO() {
        super(User.class);
    }

    @Override
    public boolean removeById(UUID id) {
        return super.removeById(id);
    }

    @Override
    public boolean update(UUID id, User obj) {
        return super.update(id, obj);
    }

    @Override
    public boolean delete(User obj) {
        return super.delete(obj);
    }

    @Override
    public List<Tuple<String, User>> findAll() {
        return super.findAll();
    }

    @Override
    public Tuple<String, User> find(UUID id) {
        return super.find(id);
    }

    @Override
    public String create(User obj) {
        return super.create(obj);
    }

    public Tuple<String, User> findByTagOneElement(String tag, String value) {
        return super.findByTagOneElement(tag, value);
    }

    @Override
    public List<Tuple<String, User>> findByTag(String tag, String value) {
        return super.findByTag(tag, value);
    }

    @Override
    public List<Tuple<String, User>> findSimpleQueries(String tag, String value) {
        return super.findSimpleQueries(tag, value);
    }

}
