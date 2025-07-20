package org.example;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class CallableDemo {

    private static StringBuffer stringBuffer = new StringBuffer();

    private static  void log(String s) { stringBuffer.append(s+"\n");}

    private static class IntegerGenerator implements Callable<Integer> {

        @Override
        public Integer call(){
            int number = new Random().nextInt(10 );

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log("thread is finished : "+number);
            return number;
        }
    }

    public static void main(String [] args) {
        LocalDateTime start = LocalDateTime.now();

        List<Future<Integer>> results = new ArrayList<>();

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        // You get "java.lang.InterruptedException: sleep interrupted" with singleThread executor as one of the thread which was
        // submitted to executor was not done and got cancelled

        //ExecutorService executorService = Executors.newFixedThreadPool(3);

        for(int i=0;i<10;i++) {
            results.add(executorService.submit(new IntegerGenerator()));
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(Future<Integer> result : results) {
            if(!result.isDone()) {
                result.cancel(true);
            } else {
                try{
                    System.out.println(result.get() + " ");
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
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

        System.out.println(stringBuffer);
    }

}
