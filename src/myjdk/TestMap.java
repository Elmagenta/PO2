package myjdk;

public class TestMap {
    public static void main(String[] args) {
        try {
            MyMap<String, Integer> m = new MyIdentityMap<>();
            m.put("alvise", 42);
            m.put("francesco", 12);
            m.put("gianni", 56);
            m.put("pippo", 78);

            int eta_di_gianni= m.get("gianni");
            System.out.println("Risultato: " + eta_di_gianni);

            m.clear();

        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }
}
