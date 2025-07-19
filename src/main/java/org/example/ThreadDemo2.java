package org.example;

public class ThreadDemo2 {

    private static StringBuffer result = new StringBuffer();

    private static void log(String s) { result.append(s+"\n");}

    public static class ThreadExample implements Runnable{

        private String name;

        public ThreadExample(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for(int i=0; i<10 ; i++) {
                log(name +" : "+i);
            }

            System.out.println("End thread : "+name);
        }

    }
    public static void main(String[] args) {

        System.out.println("Hello world starting with Thread Demo! ");

        Thread t1 = new Thread(new ThreadExample("t1"));
        Thread t2 = new Thread(new ThreadExample("t2"));

        // No guarantee that his priority will be followed
        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);

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