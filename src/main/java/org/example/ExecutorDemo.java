package org.example;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorDemo {

    private static class IntegerGenerator implements Runnable {

        @Override
        public void run() {
            int number = new Random().nextInt(10);

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread is finished, number: "+number);

        }
    }

    public static void main(String [] args) {
        LocalDateTime start = LocalDateTime.now();

        List<Future<?>> results = new ArrayList<>();

        //ExecutorService executorService = Executors.newSingleThreadExecutor();

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for(int i=0;i<10;i++) {
            results.add(executorService.submit(new IntegerGenerator()));
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(Future<?> result : results) {
            System.out.println(result.isDone() + " ");
        }

        System.out.println();

        executorService.shutdown();

        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for(Future<?> result : results) {
            System.out.println(result.isDone() + " ");
        }

        System.out.println();


        System.out.println("Time of work: "+ Duration.between(start,LocalDateTime.now()).getNano()/1000000+" milliseconds");

    }

}
