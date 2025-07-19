package org.example;

public class DemonDemo {

    public static void main(String [] args) {
        new Workerthread().start();

        try{
            Thread.sleep(7500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Main thread ends");
    }
}

class Workerthread extends Thread {

    public Workerthread() {
        setDaemon(true);
    }

    @Override
    public void run() {
        int count = 0;
        while(true) {
            System.out.println("Hello from worker "+ count++);
            try{
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}