package myjdk;

public interface MyList<T> extends MyCollection<T> {
    T get(int i) throws OutOfBoundException;
    void add(int i, T x);
    boolean remove(int i);
}
