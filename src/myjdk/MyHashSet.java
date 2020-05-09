package myjdk;

import java.util.function.Function;

public class MyHashSet<T> extends MyLinkedSet<T> {
    private HashFun<T> h;

    public interface HashFun<E> {
        long hash(E e);
    }

    public MyHashSet(HashFun<T> h) {
        super();
        this.h = h;
    }

    private static class DefaultHashFun<E> implements HashFun<E> {
        @Override
        public long hash(E e) {
            return e.hashCode();
        }
    }

    private class DefaultHashFun__nonstatic implements HashFun<T> {
        @Override
        public long hash(T e) {
            return e.hashCode();
        }
    }

    public MyHashSet() {
        super();
        /* Lambda syntax */
        this.h = (T x) -> x.hashCode();

        /* Anonymous class syntax */
        this.h = new HashFun<T>() {
            @Override
            public long hash(T e) {
                return e.hashCode();
            }
        };

        /* Method reference syntax */
        this.h = T::hashCode;

        /* I seguenti due metodi NON si trasformano automaticamente in lambda */

        /* Non-Anonymous instance */
        this.h = new DefaultHashFun<T>();

        /* Non-Anonymous non-static instance */
        this.h = new DefaultHashFun__nonstatic();
    }

    @Override
    public void add(T x) {
        MyIterator<T> it = iterator();
        boolean found = false;

        while (it.hasNext()) {
            T e = it.next();
            /*
            Metodo classico
            if (x.hashCode() == e.hashCode()) {
                found = true;
            }
            */

            /* Lambda expression */
            if (h.hash(x) == (h.hash(e)))
                found = true;
        }
        if (!found)
            l.add(x);
    }
}
