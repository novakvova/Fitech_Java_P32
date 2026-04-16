package org.example;

import org.w3c.dom.ls.LSOutput;

public class Dog extends Animal {
    //final - як як readonly у С#
    private String bread;
    // у С#
    public Dog(String name, int age, String bread) {
        super(name, age); // виклик батьківського конструктора
        this.bread = bread;
    }
    //Конструктор по замовчувані
    public Dog() {
        super("Барсик", 2);
        this.name = "Песик";
    }

    public void bark() {
        System.out.println(name + " гавкає: Гав!");
    }

    @Override
    public void eat() {
        //this.bread = "Кусачий";
        super.eat(); // викликаю батьківський метод, а потім код свого методу
        System.out.println(name + " гризе кістку");
    }
//    @Override
    // final - не можна змінювати
//    public void sleep() {
//        System.out.println("Песик спить :)");
//    }

    @Override
    public void makeSound() {
        System.out.println("Почав гавкати і кусатися.");
    }
}
