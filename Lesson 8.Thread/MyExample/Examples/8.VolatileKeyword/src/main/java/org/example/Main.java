package org.example;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        counter.start();

        Thread.sleep(600); // Let it run briefly
        counter.stop();    // Then stop the thread
    }
}