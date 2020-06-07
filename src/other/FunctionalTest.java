package other;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class FunctionalTest {

    public interface Function<A, B> {
        B apply(A x);
    }

    /* I Supplier sono delle interfacce che non prendono nulla in input e restituiscono in
    * output un qualcosa del tipo del generics dato.
    * Questa interfaccia serve per le lambda che non prendono nulla e restituisc qualcosa.
    * Una lambda che utilizza questa interfaccia può essere quindi essere inserita all'interno
    * di una variabile di tipo Supplier<A> */
    public interface Supplier<A> {
        A get();
    }

    /* Questa interafaccia invece prende qualcosa del tipo del generics dato e non ritorna
    * nulla. Questo serve per quelle lambda che dato un qualcosa non ritornano nulla ma
    * svolgono comunque un'operazione. Quindi vuol dire che una lambda che dato qualcosa
    * non ritorna nulla può essere inserita all'interno di una variabile di tipo Consumer<A> */
    public interface Consumer<A> {
        void accept(A x);
    }

    /* Questa interafaccia non prende nulla e non ritorna nulla.
    * Quindi vuol dire che data una lambda che non prende nulla e non restituisce nulla
    * può essere inserita all'interno di una variabile di tipo Runnable, i generics non
    * sono necessari in questo caso. */
    public interface Runnable {
        void run();
    }

    public static <A, B> Collection<B> map(Collection<A> l, Function<A, B> f) {
        Collection<B> c = new ArrayList<>();

        for (A a: l) {
            B b = f.apply(a);
            c.add(b);
        }

        return c;
    }

    public static <X> void print(Collection<X> c) {
        map(c, new Function<X, Void>() {
            @Override
            public Void apply(X x) {
                System.out.println(x);
                return null;
            }
        });
    }

    private static class MiaFunzionePerMap implements Function<Integer, Integer> {
        @Override
        public Integer apply(Integer x) {
            return x + 1;
        }
    }

    public static void main(String[] args) {
        List<Integer> l = new ArrayList<>();

        for (int i = 0; i < 10; ++i) {
            l.add(i);
        }

        print(l);

        Collection<Integer> r = map (l, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer x) {
                return x + 1;
            }
        });

        /* Questa è la stessa cosa fatta con una lambda */
        Collection<Integer> r2 = map(l, x -> x + 1);
        print(l);

        /* Questa terza variante usa direttamente una classe NON-Anonima */
        Collection<Integer> r3 = map(l, new MiaFunzionePerMap());

        /* La LAMBDA è uno zucchero sintattico per una anonymous class.
        * Quindi le lambda hanno lo stesso tipo dell'anonymous class che sostituiscono.
        * Una lambda in Java è compatibile non solo con il tipo Function ma con qualunque interfaccia/classe
        * con un nome qualsiasi, che abbia un solo metodo con un solo argomento ed un solo tipo di ritorno. */
        map (l, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer x) {
                System.out.println(x);
                return null;
            }
        });

        /* Spiegazione extra sulle lambda */
        Function<Integer, Integer> f1 = x -> x + 1;
        Function<Integer, Integer> f1_2 = x -> {return x + 1; }; // Stessa cosa di quella sopra ma più estesa
        Function<Integer, Integer> f2 = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer x) {
                return x + 1;
            }
        };

        /* Utilizzo dell'interfaccia Supplier per salvare una lambda che non prende nulla e
        * restituisce qualcosa. */
        Supplier<Integer> g = () -> {
            if (l.size() > 4)
                return 1;
            else
                return 0;
        };
        Supplier<Integer> g2 = new Supplier<Integer>() {
            @Override
            public Integer get() {
                if (l.size() > 4)
                    return 1;
                else
                    return 0;
            }
        };

        Consumer<Integer> h = (x) -> {
            for (int i = 0; i < x; i++)
                System.out.println(i);
        };
        Consumer<Integer> h2 = new Consumer<Integer>() {
            @Override
            public void accept(Integer x) {
                for (int i = 0; i < x; i++)
                    System.out.println(i);
            }
        };
    }
}
