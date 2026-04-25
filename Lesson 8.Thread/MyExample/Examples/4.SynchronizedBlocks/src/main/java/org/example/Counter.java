package org.example;

class Counter{

    private int c = 0;

    public void inc(){

        // Synchronize only this block
        synchronized (this) { c++; }
    }

    public int get() { return c; }
}
