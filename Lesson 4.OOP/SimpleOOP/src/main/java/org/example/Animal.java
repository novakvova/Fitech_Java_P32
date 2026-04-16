package org.example;

public abstract class Animal implements IAnimal{
    protected String name;
    protected int age;
    //Статичний лічильник об'єктів
    private static int counter = 0;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
        counter++;
    }
    //Вертає кількість створених об'єктів
    public static int getCounter() {
        return counter;
    }

    public void eat() {
        System.out.println(name + " їсть");
    }

    public final void sleep() {
        System.out.println(name + " спати");
    }
 }
