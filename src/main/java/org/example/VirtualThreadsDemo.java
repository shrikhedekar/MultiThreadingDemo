package org.example;

public class VirtualThreadsDemo {

        public static void main(String[] args) {

        System.out.println("Hello world starting with Thread Demo! ");

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Virtual Thread Demo! ");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                throw new RuntimeException(e);
                }
            }
        };

        System.out.println("Starting Virtual Thread! ");

        Thread virtualThread = Thread.startVirtualThread(runnable);

            try {
                virtualThread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("End of main");

    }
}