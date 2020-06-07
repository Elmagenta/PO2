package esercizi;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class Fibo {
    public static class Fibonacci implements Iterable {
        private int count;
        private int n;
        private int first;
        private int second;

        public Fibonacci(int number) {
            this.n =  number;
        }

        @NotNull
        @Override
        public Iterator iterator() {
            return new Iterator() {
                @Override
                public boolean hasNext() {
                    return (count < n);
                }

                @Override
                public Integer next() {
                    if (count++ >= 2) {
                        int temp = second;
                        second = first + second;
                        first = temp;
                        return second;
                    }
                    else {
                        return 1;
                    }
                }
            };
        }
    }
}
