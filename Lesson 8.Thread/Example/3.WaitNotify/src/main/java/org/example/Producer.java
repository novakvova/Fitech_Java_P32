package org.example;

//Виробник товарів
public class Producer implements Runnable {
    Store store; //Виробник має магазин куди постачає товари
    public Producer(Store store) {
        this.store = store;
    }
    @Override
    public void run() {
        for(int i = 1; i < 6; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            store.put(); // Може потавити нам 5 товарів у магазин
        }
    }
}
