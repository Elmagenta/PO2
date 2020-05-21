package esercizi;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Esame_20_06_18 {

    public static class FancyArrayList<E> extends ArrayList<E> {
        @FunctionalInterface
        public interface Function<A, B> {
            B apply(A a);
        }

        public Iterator<E> iterator(@NotNull int step, Function<E, E> f) {
            return new Iterator<>() {
                int index = step > 0 ? 0 : size() - 1;

                @Override
                public boolean hasNext() {
                    return index >= 0 && index < size() - 1;
                }

                @Override
                public E next() {
                    E current = get(index);

                    index += step;

                    return f.apply(current);
                }
            };
        }

        @Override
        @NotNull
        public Iterator<E> iterator() {
            return iterator(1, e -> e);
        }

        public Iterator<E> backwardIterator() {
            return iterator(-1, e -> e);
        }

        public static class FancyIterator<E> implements Iterator<E> {
            ArrayList<E> arr;
            Function<E,E> ef;
            int step;
            int index;

            public FancyIterator (ArrayList<E> l, int step, Function<E, E> f) {
                this.arr = l;
                this.step = step;
                this.ef = f;
                index = step > 0 ? 0 : l.size() - 1;
            }

            @Override
            public boolean hasNext() {
                return (index >= 0) && (index < arr.size() - 1);
            }

            @Override
            public E next() {
                E current = arr.get(index);

                index += step;

                return ef.apply(current);
            }
        }

    }

    public static class Pair<A, B> {
        private A a;
        private B b;

        public Pair(A a, B b) {
            this.a = a;
            this.b = b;
        }

        public static <E> Pair<E, E> findMinAndMax(List<E> l, Comparator<E> c) {
            E max = l.get(0);
            E min = l.get(0);

            for (int i = 0; i < l.size(); i++) {
                if (c.compare(l.get(i), max) > 0) {
                    max = l.get(i);
                }

                if (c.compare(l.get(i), min) < 0) {
                    min = l.get(i);
                }
            }

            return new Pair<>(min, max);
        }

        public static <E extends Comparable<E>> Pair<E, E> findMinAndMax(List<E> l) {
            return findMinAndMax(l, new Comparator<E>() {
                @Override
                public int compare(E o1, E o2) {
                    return o1.compareTo(o2);
                }
            });
        }
    }

    public static void main(String[] args) {
        List<Integer> l = new ArrayList<>();

        l.add(1);
        l.add(2);
        l.add(7);
        l.add(13);
        l.add(5);
        l.add(3);

        Pair<Integer, Integer> p = Pair.findMinAndMax(l, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        System.out.print("Min: " + p.a + "\n" + "Max: " + p.b);
        System.out.print("\n");

        Pair<Integer, Integer> p2 = Pair.findMinAndMax(l);

        System.out.print("Min: " + p2.a + "\n" + "Max: " + p2.b);
    }
}
