package other;

import zoo.Animal;
import zoo.ColoredAnimal;
import zoo.Dog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class FunctionalPrimitives {

    public static <A, B> List<B> map(Iterable<A> l, Function<? super A, ? extends B> f) {
        List<B> c = new ArrayList<>();

        for (A a : l) {
            B b = f.apply(a);
            c.add(b);
        }

        return c;
    }

    public static <A, B> List<B> map2(Iterable<A> l, Function<? super A, ? extends B> f) {
        return fold(l, new ArrayList<>(), (x, acc) -> {
            acc.add(f.apply(x));
            return acc;
        });
    }

    /* Rimuove l'elemento utilizzando la "remove" dell'iteratore */
    public static <A> Iterable<A> filter__imperative(Iterable<A> l, Function<A, Boolean> p) {
        Iterator<A> it = l.iterator();

        while (it.hasNext()) {
            A a = it.next();
            if (!p.apply(a))
                it.remove();
        }

        return l;
    }

    /* Crea una nuova lista a cui vengono aggiunti gli elementi corretti */
    public static <A> Iterable<A> filter__pure(Iterable<A> l, Function<A, Boolean> p) {
        List<A> r = new ArrayList<>();

        for (A a : l) {
            if (p.apply(a))
                r.add(a);
        }

        return r;
    }

    public static <A, B> B fold(Iterable<A> l, B zero, BiFunction<A, B, B> f) {
        B acc = zero;

        for (A a : l) {
            acc = f.apply(a, acc);
        }

        return acc;
    }

    public static <A, B> B fold_recur(Iterable<A> l, B zero, BiFunction<A, B, B> f) {
        return fold_recur(l.iterator(), zero, f);
    }

    public static <A, B> B fold_recur(Iterator<A> it, B zero, BiFunction<A, B, B> f) {
        return it.hasNext() ? fold_recur(it, f.apply(it.next(), zero), f) : zero;
    }

    @FunctionalInterface
    public interface TriFunction <A, B, C, R> {
        R apply(A a, B b, C c);
    }

    public static void main(String[] args) {
        /* Lista di Integer: spiegazione della map */
        List<Integer> l = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            l.add(i);
        }

        Collection<Integer> r1 = map(l, (x) -> x * 2);

        /* Lista di Cani: Spiegazione dei wildcards utilizzati per dare in input alla map qualcosa di superiore a Dog e
         * dare come tipo di ritorno qualcosa di più specifico di Dog. */
        List<Dog> l2 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            l2.add(new Dog(i, "red"));
        }

        List<Animal> r2 = map(l2, (ColoredAnimal x) -> new Dog(x.getWeight() - 5, x.getColor()));

        /* Spiegazione della fold */
        List<Integer> l3 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            l.add(i);
        }

        Collection<Integer> r3 = map(l, (x) -> x * 2);
        int sum = fold(r3, 0, (x, acc) -> x + acc);

        /* Spiegazione dell'estrazione del "method reference".
        * Tutti i metodi chbe non prendono argomenti, dal punto di vista funzionale si trasformano in funzioni con almeno
        * un argomento che è il tipo di ritorno del metodo. */
        String s = "ciao";
        boolean b = s.isEmpty();
        Function<String, Boolean> f = String::isEmpty;

        int i = s.indexOf('c');
        BiFunction<String, Character, Integer> h = String::indexOf;

        int j = s.lastIndexOf('c', 8);
        TriFunction<String, Character, Integer, Integer> g = String::lastIndexOf;

        List<String> l4 = new ArrayList<>();
        l4.add("pippo");
        l4.add("baudo");
        l4.add("pluto");
        String s2 = String.join(",", l4);
        BiFunction<CharSequence, Iterable<? extends CharSequence>, String> k = String::join;
    }
}
