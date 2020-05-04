package myjdk;

public interface MyMap<K, V> extends MyCollection<Pair<K, V>> {
    V get(K k);
    default void add(K k, V v) {
        add(new Pair<>(k, v));
    }
}
