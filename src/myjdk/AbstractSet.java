package myjdk;

public abstract class AbstractSet<T> implements MySet<T> {

    protected MyLinkedList<T> l;

    public AbstractSet() {
        this.l = new MyLinkedList<>();
    }

    public abstract void add(T x);

    @Override
    public int size() {
        return l.size();
    }

    @Override
    public boolean contains(T x) {
        return l.contains(x);
    }

    @Override
    public boolean remove(T x) {
        return l.remove(x);
    }

    @Override
    public void clear() {
        l.clear();
    }

    @Override
    public MyIterator<T> iterator() {
        return l.iterator();
    }
}
