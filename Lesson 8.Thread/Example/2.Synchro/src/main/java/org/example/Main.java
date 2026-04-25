package org.example;

public class Main {
    public static void SimpleThread() {
        Counter counter = new Counter();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join(); //очікує завершення роботи
            t2.join(); //очікує завершення роботи
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(counter.getCount());
    }

    public static void main(String[] args) {
        System.out.println("--Синхронізація потоків--");
        //SimpleThread();
        Thread t1 = new Thread(() -> Table.printTable(12));
        Thread t2 = new Thread(() -> Table.printTable(9));
        t1.start();
        t2.start();
    }
}