package org.example;

public interface IAnimal {
    //Статична змінна final та static
    // максимальний вік тварини 100 рочків
    int MAX_AGE = 250;
    void eat();
    void sleep();
    void makeSound();
    default void eatBear() {
        System.out.println("Тут може бути ведмідь :). Ням ням ням...");
    }

    static void helloMyFriends() {
        System.out.println("Це статичний метод в інтерфейсі");
    }

}
