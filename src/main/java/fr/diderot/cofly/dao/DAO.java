package fr.diderot.cofly.dao;

import fr.diderot.cofly.utils.Tuple;
import java.util.List;
import java.util.UUID;

/**
 * V is a generic for all objects from "metier" which can be stored in the
 * website database.
 *
 * @param <V>
 */
public interface DAO<V> {

    /**
     * Create an object V in the database.
     *
     * @param obj
     * @return
     */
    public abstract String create(V obj);

    /**
     * Search in the database the object V.
     *
     * @param id
     * @return
     */
    public abstract Tuple<String, V> find(UUID id);
    
    
    /**
     *
     *
     * @return
     */
    public abstract List<Tuple<String, V>> findAll();

    /**
     * Remove an object V of the database.
     *
     * @param obj
     * @return
     */
    public abstract boolean delete(V obj);

    /**
     * Modifies the data in the database of an object V.
     *
     * @param id
     * @param obj
     * @return
     */
    public abstract boolean update(UUID id, V obj);

    /**
     *
     *
     * @param id
     * @return
     */
    public abstract boolean removeById(UUID id);
}
