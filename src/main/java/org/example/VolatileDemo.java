package org.example;

public class VolatileDemo {

    public static volatile boolean running = true;
    private static StringBuffer result = new StringBuffer();

    private static void log(String s) { result.append(s+"\n");}

        public static void main(String[] args) {

        System.out.println("Hello world starting with Thread Demo! ");

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int counter =0;
                while(running) {
                    counter++;
                }
                log("Tread 1 finished. Counted up to "+counter);
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log("Thread 2 finishing");
                running = false;
            }
        });

        System.out.println("Starting Thread! ");
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Result :\n"+result);

        System.out.println("End of main thread! ");



    }
}