package org.example;

public class Table {
    synchronized static void printTable(int n){

        for (int i = 1; i <=3; i++){

            System.out.println(n * i);
            try {
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
