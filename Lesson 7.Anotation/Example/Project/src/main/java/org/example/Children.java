package org.example;

public class Children extends Parent {
    @Override
    @LogRuntimeInfo(value="Добре їсти добре спати")
    public void display() {
        System.out.println("Привіт - це син");
    }
}
