package org.example;

public class CookingJob implements Runnable {
    private String task;

    public CookingJob(String task) {
        this.task = task;
    }
    @Override
    public void run() {
        System.out.println("CookingJon: " + task
                +"\t Thread: " + Thread.currentThread().getName());
    }
}
