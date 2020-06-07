package patterns;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Singleton {

    public static class Display {
        public int test;

        @Nullable
        private static Display instance =  null;

        /* Mettendo il costruttore privato questo può essere acceduto solo dai membri della classe */
        private Display(int n) {
            this.test = n;
        }

        @NotNull
        public static Display getInstance(int n) {
            if (instance == null) {
                instance = new Display(n);
            }

            return instance;
        }
    }

    public static void main(String[] args) {
        Display d = Display.getInstance(10);
        Display d2 = Display.getInstance(20);

        System.out.println(d.test);
        System.out.println(d2.test);
    }
}
