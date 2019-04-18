package fr.diderot.cofly.utils;

/**
 *
 * @param <K>
 * @param <V>
 */
public class Tuple<K, V> {

    public final K key;
    public final V value;

    public Tuple(K k, V v) {
        this.key = k;
        this.value = v;
    }

    @Override
    public String toString() {
        return key.toString() + "\n" + value.toString();
    }

}
