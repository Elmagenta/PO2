package myjdk;

public class MyHashMap<K, V> implements MyMap<K, V> {
    @Override
    public V get(K k) {
        return null;
    }

    @Override
    public void add(Pair<K, V> x) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean contains(Pair<K, V> x) {
        return false;
    }

    @Override
    public boolean remove(Pair<K, V> x) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public MyIterator<Pair<K, V>> iterator() {
        return null;
    }
}
