package myjdk;

import java.util.function.Function;

public class MyHashSet<T> extends MyLinkedSet<T> {
    private Function<T, Long> h;

    public MyHashSet(Function<T, Long> h) {
        super();
        this.h = h;
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
            if ((long) h.apply(x) == (long) (h.apply(e)))
                found = true;
        }
        if (!found)
            l.add(x);
    }
}
