package other;

import java.util.ArrayList;
import java.util.Iterator;

public class RevArrayList<T> extends ArrayList<T> {
    public RevArrayList() {
        super();
    }

    public RevArrayList(int cap) {
        super(cap);
    }

    public static class RevIterator__static<T> implements Iterator<T> {
        private RevArrayList<T> l;
        private int pos;

        public RevIterator__static(RevArrayList<T> l) {
            this.l = l;
            this.pos = l.size() - 1;
        }

        @Override
        public boolean hasNext() {
            return pos >= 0;
        }

        @Override
        public T next() {
            return l.get(pos--);
        }
    }

    private class RevIterator__nonstastic implements Iterator<T> {
        private int pos = RevArrayList.this.size() - 1;

        @Override
        public boolean hasNext() {
            return pos >= 0;
        }

        @Override
        public T next() {
            return RevArrayList.this.get(pos--);
        }
    }

    @Override
    public Iterator<T> iterator() {
//        return new RevIterator<T>(this);          // Costruzione globale
//        return new RevIterator__static<>(this);   // Costruzione nested STATICA
//        return new RevIterator__nonstastic();     // Costruzione nested NON STATICA

        return new Iterator<T>() {                  // Anonymous Class
            private int pos = size() - 1;

            @Override
            public boolean hasNext() {
                return pos >= 0;
            }

            @Override
            public T next() {
                return get(pos--);
            }
        };
    }
}
