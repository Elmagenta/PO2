package other;

import java.util.Iterator;

public class RevTest {
    public static void main(String[] args) {
        RevArrayList<Integer> l = new RevArrayList<>();

        l.add(35);
        l.add(11);
        l.add(23);

        Iterator<Integer> it = l.iterator();
        while (it.hasNext()) {
            int n = it.next();
            System.out.println(n);
        }
    }
}
