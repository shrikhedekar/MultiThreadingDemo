package org.example;

public class LostUpdateDemo {

    private static class SynchronizedThread implements Runnable {

        private int value = 0;

        @Override
        public void run() {
            for(int i=0; i<5000; i++) {
                increment();
                Thread.yield();
            }
        }

       // public  void  increment() {
       // Not adding synchronized will result in lost update as both threads will try to update
       // the variable in parallel so final result will be less than 10000
        public synchronized void  increment() {
            int i= value;
            value = i +1;
        }

        public int getValue () { return value; }

    }

    public static void main(String [] args) {
        SynchronizedThread job = new SynchronizedThread();
        Thread t1 = new Thread(job,"t1");
        Thread t2 = new Thread(job, "t2");
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(job.getValue());
    }



}
