package org.example;

public class Store {
    private int product = 0;

    public synchronized void get() {
        while(product < 1) {
            try {
                System.out.println("Покупець очікує товари у магазині... " + product);
                //По суті ми зупиняємо роботу потоку, коли нам інший потів не поставить товар
                wait(); // Очікуємо коли продукти подявляться в магазині - очікує notify()
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        product--;
        System.out.println("Клієнт купив 1 товар");
        System.out.println("Кількість товарів на складі: "+ product);
        notify(); //повідомить, то те, що продукт був куплений у магазині
    }
    public synchronized void put() {
        while(product >= 3) {
            try {
                wait(); //Зупиняється, щоб клієнт міг купити товари
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        product++;
        System.out.println("Виробник додав 1 товар");
        System.out.println("Кількість товарів на складі: "+ product);
        notify();
    }
}
