package org.example;

public class Main {
    public static void main(String[] args) {
//        Thread t = new Thread(new CookingTask("Pasta"));
//        Thread t2 = new CookingTask("Pizza");
//        Thread t3 = new CookingTask("Salad");
//        Thread t4 = new CookingTask("Risotto");
//        t.start();
//        t2.start();
//        t3.start();
//        t4.start();
        Thread job1 = new Thread(new CookingJob("Pasta"));
        Thread job2 = new Thread(new CookingJob("Борщ"));
        Thread job3 = new Thread(new CookingJob("Олів'є"));
        job1.start();
        job2.start();
        job3.start();

        System.out.println("Main thread: " + Thread.currentThread().getName());
    }
}