package myjdk;

public class MyIdentityMap<K, V> implements MyMap<K, V> {
    private MyArrayList<Pair<K, V>> m;

    @Override
    public V get(K k) throws NotFoundException {
        MyIterator<Pair<K, V>> it = m.iterator();

        while (it.hasNext()) {
            Pair<K, V> p = it.next();

            if (p.first.equals(k))
                return p.second;
        }
        throw new NotFoundException();
    }

    /* Nel caso di questa mappa se volessi farla "Hash" dovrei usare l'hashcode dell'oggetto
     * e non utilizzando il metodo equals. In questo caso invece viene fatto un confronto
     * tra identità. */

    @Override
    public void put(K k, V v) {
        MyIterator<Pair<K, V>> it = m.iterator();

        while (it.hasNext()) {
            Pair<K, V> p = it.next();

            if (p.first.equals(k)) {
                // TODO: fare la stessa cosa con le coppie immutabili
                p.second = v;
                return;
            }
        }
        m.add(new Pair<>(k, v));
    }

    @Override
    public void clear() {
        m = new MyArrayList<>();
    }
}
