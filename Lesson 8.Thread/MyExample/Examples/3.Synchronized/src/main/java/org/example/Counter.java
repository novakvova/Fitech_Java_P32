package org.example;

class Counter{

    // Shared variable
    private int c = 0;

    // Synchronized method to increment counter
    public synchronized void inc(){
        c++;

    }

    // Synchronized method to get counter value
    public synchronized int get(){
        return c;

    }
}
