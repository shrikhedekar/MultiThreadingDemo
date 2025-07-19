package org.example;

public class ThreadDemo {

    public static class ThreadExample implements Runnable{

        private String name;

        public ThreadExample(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for(int i=0; i<10 ; i++) {
                System.out.println(name +" : "+i);
            }

            System.out.println("End thread : "+name);
        }

    }
    public static void main(String[] args) {

        System.out.println("Hello world starting with Thread Demo! ");

        Thread t = new Thread(new ThreadExample("t"));
        System.out.println("Starting Thread! ");
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("End of main thread! ");



    }
}