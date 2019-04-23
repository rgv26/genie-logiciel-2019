package fr.diderot.cofly.dao;

import fr.diderot.cofly.database.Search;
import fr.diderot.cofly.metier.Pilot;
import fr.diderot.cofly.metier.User;
import fr.diderot.cofly.utils.Tuple;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ImplementationDAO<V> implements DAO<V> {

    protected Class<V> tClass;

    public ImplementationDAO() {

    }

    public ImplementationDAO(Class<V> tclass) {
        this.tClass = tclass;
    }

    @Override
    public String create(V obj) {
        try {
            return Search.add(obj, tClass.getSimpleName().toLowerCase());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Tuple<String, V> find(UUID id) {
        try {
            return Search.find(id, tClass);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Tuple<String, V>> findAll() {
        try {
            return Search.find(tClass.getSimpleName().toLowerCase(), tClass);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(V obj) {
        try {
            return Search.remove(obj.getClass().getSimpleName().toLowerCase());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(UUID id, V obj) {
        try {
            return Search.update(id, obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removeById(UUID id) {
        try {
            return Search.removeById(tClass.getSimpleName().toLowerCase(), id);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public Tuple<String, V> findByTagOneElement(String tag, String value) {
        try {
            return  Search.findByTagOneElement(tag, value, tClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Tuple<String, V>> findByTag(String tag, String value) {
        try {
            return Search.findByTag(tag, value, tClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Tuple<String, V>> findSimpleQueries(String tag, String value) {
        try {
            return Search.findSimpleQueries(tClass.getSimpleName().toLowerCase(), tag, value, tClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean removeAll() {
        Set<String> set;
        Iterator<String> ite;

        try {
            set = Search.getAllIndex();
            ite = set.iterator();

            if (set.isEmpty()) {
                return true;
            } else {
                while (ite.hasNext()) {
                    String index = ite.next();
                    try {
                        if (!Search.remove(index)) {
                            return false;
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                return true;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
