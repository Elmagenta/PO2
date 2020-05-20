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
    }
}
