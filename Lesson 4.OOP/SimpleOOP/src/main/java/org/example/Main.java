package org.example;

public class Main {
    public static void main(String[] args) {
//        String str="Привіт! Сьогодні дуже гарно.";
//        System.out.println(str.toLowerCase());
        var bobic = new Dog();
        bobic.eat();

        Animal animal = new Dog("Бобік", 3, "Вовчарка Німецька");
        animal.eat();
        // is - С#,
//        if(animal instanceof Dog) {
//            var dog = (Dog) animal;
//            dog.bark();
//        }
        if(animal instanceof Dog dog) {
            dog.bark();
        }
        System.out.println("Кількість створених об'єктів "+ Animal.getCounter());
        System.out.println("Максимальний вік "+ Animal.MAX_AGE);
        animal.eatBear();

        IAnimal.helloMyFriends();
        Object obj1 = new Object();
        Object obj2 = new Object();
        Object obj3 = new Object();
        obj1 = null; // більше не доступний
        obj2 = new Object();
        obj3 = obj2;
        System.gc(); // виклики явно GB
    }
}