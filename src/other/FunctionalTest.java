package other;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class FunctionalTest {

    public static <A, B> Collection<B> map(Collection<A> l, Function<A, B> f) {
        Collection<B> c = new ArrayList<>();

        for (A a: l) {
            B b = f.apply(a);
            c.add(b);
        }

        return c;
    }

    public static void main(String[] args) {
        List<Integer> l = new ArrayList<>();

        for (int i = 0; i < 10; ++i) {
            l.add(i);
        }

        Collection<Integer> r = map (l, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer x) {
                return x + 1;
            }
        });

        map (l, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer x) {
                System.out.println(x);
                return null;
            }
        });
    }
}
