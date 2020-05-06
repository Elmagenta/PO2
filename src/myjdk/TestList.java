package myjdk;

public class TestList {
    public static void main(String[] args) {
        try {
            MyList<Integer> a = new MyArrayList<>();

            a.add(23);
            a.add(11);

            int n = a.get(10);
            System.out.println("Elemento in posizione 1: " + n);
        } catch (OutOfBoundException e) {
            System.out.println("Eccezione: " + e.getMessage());
        }
    }
}
