package threading;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class ThreadCreation {

    private static void count(String id, long millis, int times) {
        try {
            for (int i = 0; i < times; i++) {
                System.out.println(id + ": " + i);
                Thread.sleep(millis);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* La classe "Thread" è una classe con il metodo "run" che non fa nulla e va fatto l'override */
    public static class MyThread extends Thread {
        @Override
        public void run() {
            count("Thread #1", 300, 10);
        }
    }

    private static Random rnd = new Random();
    private static int rand(int a, int b) {
        return rnd.nextInt(b - a + 1) + a;
    }

    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.start(); /* Finché non scrivo questo il thread non parte */

        /* L'interfaccia "Runnable" è compatibile con le lambda che non prendono niente
        * e non ritornano niente. Questo accade perché in Java non posso definire una funzione
        * che non prende niente e non restituisce nulla, ovvero una Function<Void, Void>.
        * Creazione di un thread tramite il passaggio di un Runnable al costruttore.
        * Runnable = Lambda senza argomenti e senza ritorno */
        Runnable f = () -> {
            count("Thread #1", 400, 20);
        };
        Thread t2 = new Thread(f);
        t2.start();

        Collection<Thread> threads = new ArrayList<>();
        for (int i = 0; i < rand(0, 20); i++) {
            final String name = String.format("Thread #%d", i);
            final long millis = rand(200, 800);
            final int times = rand(10, 30);

            /* Creazione di un thread tramite una lambda expression */
            Thread t3 = new Thread(() -> {
                count(name, millis, times);
                /* Posso creare un Thread al volo che STAMPA, poiché in questo caso non mi interessa
                * tenere il valore di ritorno ma mi interessa soltanto che venga stampata la linea. */
                new Thread(() -> System.out.println("ciao")).start();
            });

            /* Stessa cosa della precedente, ma la lambda è dezuccherata in un Runnable */
            Thread t_3 = new Thread(new Runnable() {
                @Override
                public void run() {
                    count(name, millis, times);
                }
            });

            /* Creazione di un thread tramite una anonymous class con override al volo del metodo run */
            Thread t4 = new Thread() {
                @Override
                public void run() {
                    count(name, rand(200, 800), rand(10, 30));
                }
            };

            t3.start();
            threads.add(t3);
        }

        /* Per aspettare un thread devo chiamare il "join" di quel thread */
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
