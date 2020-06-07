package esercizi;

import myjdk.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class Esercizi_2_06 {

    private static <A> Iterator<Pair<A, A>> reverseIterator (Iterator<Pair<A,A>> collection) {
        return new Iterator<Pair<A, A>>() {
            @Override
            public boolean hasNext() {
                return collection.hasNext();
            }

            @Override
            public Pair<A, A> next() {
                Pair<A, A> pair = collection.next();
                A first = pair.first;
                A second = pair.second;

                pair.second = first;
                pair.first = second;

                return pair;
            }
        };
    }

    /* Metodo statico che data una Collection di Pair<A, A> torni una nuova coppia di elementi rovesciati */
    private static <A> Iterator<Pair<A, A>> reverseCollection (Collection<Pair<A,A>> collection) {
        Iterator<Pair<A, A>> it = collection.iterator();

        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public Pair<A, A> next() {
                Pair<A, A> pair = it.next();

                return new Pair<>(pair.second, pair.first);
            }
        };
    }

    /* Iteratore che applica ai suoi elementi una Function<A, B> tornando il risultato */
    public class mapIterator<A> implements Iterator<A> {
        private Collection<A> c;
        private Function<A, ?> f;
        private Iterator<A> it;

        public <B> mapIterator(Collection<A> collection, Function<A, B> function) {
            this.c = collection;
            this.f = function;
            it = c.iterator();
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public A next() {
            A local = it.next();
            f.apply(local);
            return local;
        }
    }

    /* Stessa cosa ma usando una Lista */
    public class mapList<A> implements Iterator<A> {
        private List<A> c;
        private Function<A, ?> f;
        private int pos = 0;
        private int dim;

        public <B> mapList(List<A> collection, Function<A, B> function) {
            this.c = collection;
            this.f = function;
            this.dim = collection.size();
        }

        @Override
        public boolean hasNext() {
            return pos < dim;
        }

        @Override
        public A next() {
            f.apply(c.get(pos));
            return c.get(pos++);
        }
    }

    public static class Fattoria {

        private interface Docenti {
            void materia();
        }

        public static class Spanò implements Docenti {
            public List<String> l;

            public Spanò(List<String> materie) {
                this.l = materie;
            }

            @Override
            public void materia() {
                for (String s : l) {
                    System.out.println(s);
                }
            }
        }

        public static class Fabrizio implements Docenti {
            public List<String> l;

            public Fabrizio(List<String> materie) {
                this.l = materie;
            }

            @Override
            public void materia() {
                for (String s : l) {
                    System.out.println(s);
                }
            }
        }

        public static class Prove {
            public interface Shape {
                void draw();
            }

            public static class Cube implements Shape {

                @Override
                public void draw() {
                    System.out.println("Ciaso");
                }

                public void getLato() {
                    System.out.println("Lato");
                }
            }

            public static void main(String[] args) {
                Shape s = new Cube();

                s.draw();
            }
        }

    }



}
