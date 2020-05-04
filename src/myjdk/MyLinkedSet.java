package myjdk;

public class MyLinkedSet<T> extends AbstractSet<T> {

    @Override
    public void add(T x) {
        if (!l.contains(x))
            l.add(x);
    }

}
