package myjdk;

import java.util.Objects;

public class MyArrayList<T> implements MyList<T> {

    private Object[] a;
    private int actualSize;

    public MyArrayList() {
        this.a = new Object[100];
        this.actualSize = 0;
    }

    @Override
    public T get(int i) throws OutOfBoundException {
        if (i >= actualSize)
            throw new OutOfBoundException("Get: Invalid position " + i);
        //noinspection unchecked
        return (T)a[i];
    }

    @Override
    public int size() {
        return actualSize;
    }

    @Override
    public void add(T x) {
        if (actualSize >= a.length) {
            Object[] a2 = new Objects[a.length + 100];

            System.arraycopy(a, 0, a2, 0, a.length);
            a = a2;
        }
        a[actualSize++] = x;
    }

    @Override
    public void add(int i, T x) {
        for (int j = 0; j < a.length; j++) {
            if (j == i) {
                a[j] = x;

                return;
            }
        }
    }

    @Override
    public boolean remove(int i) {
        if (i > actualSize) {
            return false;
        }
        else {
            for (int j = 0; j < actualSize; j++) {
                if (j == i) {
                    a[j] = null;
                }
            }

            return true;
        }

    }

    @Override
    public boolean contains(T x) {
        // TODO: questa è una brutta replicazione: pensare ad un buon refactoring
        MyIterator<T> it = iterator();

        while (it.hasNext()) {
            T e = it.next();
            if (x.equals(e))
                return true;
        }

        return false;
    }

    @Override
    public boolean remove(T x) {
        return false;
    }

    @Override
    public void clear() {
        this.actualSize = 0;
    }

    @Override
    public MyIterator<T> iterator() {
        return new MyIterator<>() {

            private int pos = 0;

            @Override
            public boolean hasNext() {
                return pos < actualSize;
            }

            @Override
            public T next() {
                //noinspection unchecked
                return (T)a[pos++];
            }
        };
    }
}
