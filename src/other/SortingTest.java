package other;

import org.jetbrains.annotations.NotNull;
import zoo.Dog;

import java.util.*;

public class SortingTest {
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();

        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        Random rand = new Random();
        List<Integer> l = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            l.add(rand.nextInt(100));
        }

        List<String> l2 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            l2.add(randomAlphaNumeric(10));
        }

        List<Dog> l3 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            l3.add(new Dog(rand.nextInt(100), randomAlphaNumeric(5)));
        }

        List<Rectangle> l4 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            l4.add(new Rectangle(rand.nextDouble() * 100., rand.nextDouble() * 100.));
        }

        List<Square> l5 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            l5.add(new Square(rand.nextDouble() * 10.));
        }

        System.out.println(l);
        Collections.sort(l);
        System.out.println(l);

        System.out.println(l2);
        Collections.sort(l2);
        System.out.println(l2);

        System.out.println(l3);
        Collections.sort(l3);
        System.out.println(l3);

        /* In questo caso la classe Rectangle implementata sotto non implementa la classe "Comparable" quindi in teoria
        * non è confrontabile e quindi di conseguenza non è nemmeno ordinabile. Passando però al volo un Comparator
        * con dentro un Rectangle come secondo argomento della "sort" allora posso confrontare la mia lista di rettangoli
        * su uno specifico argomento che vado a definire al volo ed ordinarla anche senza che essa implementi l'interfaccia
        * "Comparable" */
        System.out.println(l4);
        Collections.sort(l4, new Comparator<Rectangle>() {
            @Override
            public int compare(Rectangle o1, Rectangle o2) {
                /* Se i due valori interi sono uguali rimangono nel loro posto e quindi si hanno degli errori nella
                * lista ordinata */
                // return (int) o1.base - (int) o2.base;

                /* Confronto a mano tra due double
                if (Math.abs(o1.base - o1.base) <= Double.MIN_VALUE) {
                    return 0;
                }
                else if (o1.base - o2.base > 0.){
                    return 1;
                }
                else {
                    return -1;
                }
                */

                /* Confronto a mano tra due double con if funzionale
                return Math.abs(o1.base - o1.base) <= Double.MIN_VALUE ? 0 : o1.base - o2.base > 0. ? 1 : -1;
                 */

                return Double.compare(o1.base, o2.base);
            }
        });
        System.out.println(l4);
        Collections.sort(l4, (o1, o2) -> Double.compare(o1.height, o2.height));
        System.out.println(l4);
    }

    /* Implementazione al volo per non andare a sporcare la classe "Rectangle" */
    private static class Rectangle {
        public final double base, height;

        public Rectangle(double b, double h) {
            this.base = b;
            this.height = h;
        }

        public double area() {
            return base * height;
        }

        public double perimeter() {
            return (base + height) * 2;
        }

        @Override
        public String toString() {
            return String.format("Rectangle[%g x %g]", base, height);
        }
    }

    private static class Square extends Rectangle {

        public Square(double l) {
            super(l, l);
        }
    }
}
