package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicVariableDemo {

    private static final int ITERATIONS = 1000000;

    //private static int counter =0;
// If you use primitive int then you will not get desired
    // output of 100000000 as it's increment operation will
    // not be thread safe by default. You need to synchronized
    // it explicitly or can us AtomicInteger whose operations
    // are synchronized by default.


    private static AtomicInteger counter =new AtomicInteger(0);


    private static class ThreadExample implements Runnable {

        private String name;

        public ThreadExample(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 0; i < ITERATIONS; i++) {
                counter.getAndIncrement();
            }
        }
    }

    public static void main(String[] args) {

        List<Thread> threads= new ArrayList<>();

        for (int i=0;i<100;i++) {
            threads.add(new Thread(new ThreadExample("t"+i)));
        }

        System.out.println("Starting threads");
        for (int i=0;i<100;i++) {
            threads.get(i).start();
        }

        try {
            for (int i=0;i<100;i++) {
                threads.get(i).join();
            }
        } catch (InterruptedException e) {
                throw new RuntimeException(e);
        }

        System.out.println("Counter= "+counter.get());


    }

}
