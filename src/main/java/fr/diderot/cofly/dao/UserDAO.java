package fr.diderot.cofly.dao;

import fr.diderot.cofly.metier.User;
import fr.diderot.cofly.utils.Tuple;
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

    @Override
    public boolean checkEmail(String email) {
        return super.checkEmail(email);
    }
}
