package threading;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConsumerProducer {

    private static Random rnd = new Random();

    private static synchronized int rand(int a, int b) {
        return rnd.nextInt(b - a + 1) + a;
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

    public static class Consumer extends Thread {
        private List<Integer> l;

        public Consumer(List<Integer> l) {
            this.l = l;
        }

        @Override
        public void run() {
            long ms = rand(1, 10);

            while (true) {
                synchronized (l) {
                    if (!l.isEmpty()) {
                        int n = l.remove(0);

                        log(String.format("Consumer: pop: %d", n));
                    }
                }

                try {
                    Thread.sleep(ms);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class Producer extends Thread {
        private List<Integer> l;
        private int counter = 0;

        public Producer(List<Integer> l) {
            this.l = l;
        }

        @Override
        public void run() {
            while (true) {
                long ms = rand(1, 10);
                int n = counter++;

                synchronized (l) {
                    l.add(n);
                    log(String.format("Producer: push: %d (size: %d) %s", n, l.size(), l));
                }

                try {
                    Thread.sleep(ms);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            List<Integer> l = new ArrayList<>();
            Consumer c = new Consumer(l);
            Producer p = new Producer(l);
            c.start();
            p.start();

            c.join();
            p.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
