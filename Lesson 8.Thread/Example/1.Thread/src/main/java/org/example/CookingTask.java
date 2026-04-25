package org.example;

import java.lang.annotation.Target;

public class CookingTask extends Thread {
    private String task;

    public CookingTask(String task) {
        this.task = task;
    }

    @Override
    public void run() {
        System.out.println("Cooking: " + task
                +"\t Thread: " + Thread.currentThread().getName());
    }
}
