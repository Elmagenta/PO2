package threading;

public class Main {
    public static class MyThread extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Thread: " + i);
                    Thread.sleep(300);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyThread t = new MyThread();

        t.start(); /* Finché non scrivo questo il thread non parte */

        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("Main: " + i);
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
