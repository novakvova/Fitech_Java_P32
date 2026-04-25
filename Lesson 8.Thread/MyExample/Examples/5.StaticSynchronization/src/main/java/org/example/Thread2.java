package org.example;

class Thread2 extends Thread {
    public void run() {
        Table.printTable(10);
    }
}
