package threading;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConsumerProducer {

    private static Random rnd = new Random();

    private static int rand(int a, int b) {
        return rnd.nextInt(b - a + 1) + a;
    }

    // Se fosse necessario synchronized
    private static synchronized int rand__sync(int a, int b) {
        return rnd.nextInt(b - a + 1) + a;
    }

    // Se fosse necessario synchronized dezuccherata
    private static synchronized int rand__sync_desugared(int a, int b) {
        synchronized (ConsumerProducer.class) {
            return rnd.nextInt(b - a + 1) + a;
        }
    }

    /* Fa la stessa cosa del synchronized sul metodo, ma fa qualcosa di più fine anche
    * se è da fare manualmente. Entrambi i metodi vanno bene. */
    private static int rand2(int a, int b) {
        Lock l = new ReentrantLock();
        l.lock();
        try {
            return rnd.nextInt(b - a + 1) + a;
        }
        finally {
            l.unlock();
        }
    }

    private static void log(String msg) {
        Thread self = Thread.currentThread();
        System.out.println(String.format("%s[%d]: %s", self.getName(), self.getId(), msg));
    }

    /* Questo oggetto verrà usato soltanto come semaforo, non fa altro.
    * È per dimostrare che posso sincronizzare non soltanto con gli oggeti che sto usando
    * dal ConsumerProducer ma anche con altri oggetti, l'importante è utilizzare lo stesso
    * oggetto per sincronizzare le due parti. */
    private static Object dummy = new Object();

    public static class Consumer extends Thread {
        private List<Integer> l1;
        private List<Integer> l2;

        public Consumer(List<Integer> l1, List<Integer> l2) {
            this.l1 = l1;
            this.l2 = l2;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (dummy) {
                    if (!l1.isEmpty() && !l2.isEmpty()) {
                        int n1 = l1.remove(0);
                        int n2 = l1.remove(0);

                        log(String.format("Consumer: pop: %d, %d (size: %d, %d) %s %s", n1, n2, l1.size(), l2.size(), l1, l2));
                    }
                }

                try {
                    Thread.sleep(rand(1, 50));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class Producer extends Thread {
        private List<Integer> l1;
        private List<Integer> l2;
        private int counter = 0;

        public Producer(List<Integer> l1, List<Integer> l2) {
            this.l1 = l1;
            this.l2 = l2;
        }

        @Override
        public void run() {
            while (true) {
                int n = counter++;

                synchronized (dummy) {
                    l1.add(n);
                    l2.add(n);
                    log(String.format("Producer: push: %d (size: %d, %d) %s %s", n, l1.size(), l2.size(), l1, l2));
                }

                try {
                    Thread.sleep(rand(1, 50));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            List<Integer> l1 = new ArrayList<>();
            List<Integer> l2 = new ArrayList<>();
            Consumer c = new Consumer(l1, l2);
            Producer p1 = new Producer(l1, l2);
            Producer p2 = new Producer(l1, l2);
            c.start();
            p1.start();
            p2.start();

            /* Anonymous class per replicare il codice del Producer, in questo caso però avendo due Producer dovrei copiare
            * il codice due volte. Quindi IN QUESTO CASO non vale la pena farla, poiché il codice viene copiato due volte.
            * Pertanto conviene fare una classe normale per evitare di ripetere il codice. */
            Thread p3 = new Thread() {
                private int counter = 0;

                @Override
                public void run() {
                    while (true) {
                        int n = counter++;

                        synchronized (dummy) {
                            l1.add(n);
                            l2.add(n);
                            log(String.format("Producer: push: %d (size: %d, %d) %s %s", n, l1.size(), l2.size(), l1, l2));
                        }

                        try {
                            Thread.sleep(rand(1, 50));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };

            /* Caso SIMILE al precedente ma non bisogna confondersi.
            * In questo caso NON sto definendo una classe anonima, ma sto costruendo al volo un nuovo Runnable
            * che passerò al costruttore di Thread in maniera che venga costruito il mio nuovo Thread. */
            Thread p3_2 = new Thread(new Runnable() {
                private int counter = 0;

                @Override
                public void run() {
                    while (true) {
                        int n = counter++;

                        synchronized (dummy) {
                            l1.add(n);
                            l2.add(n);
                            log(String.format("Producer: push: %d (size: %d, %d) %s %s", n, l1.size(), l2.size(), l1, l2));
                        }

                        try {
                            Thread.sleep(rand(1, 50));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            /* Stessa cosa di quella sopra ma con le lambda */
            Thread p3_3 = new Thread(() -> {
                int counter = 0;

                while (true) {
                    int n = counter++;

                    synchronized (dummy) {
                        l1.add(n);
                        l2.add(n);
                        log(String.format("Producer: push: %d (size: %d, %d) %s %s", n, l1.size(), l2.size(), l1, l2));
                    }

                    try {
                        Thread.sleep(rand(1, 50));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            c.join();
            p1.join();
            p2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
