package org.example;

import org.example.myclasses.Box;

import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void template() {
        //        Box<Integer> myBox = new Box<>();
        Box<Integer> myBox =  new Box<>();
        myBox.setContent(23);
        System.out.println(myBox.getContent());
        //Шаблоний тип може працювати лише з об'єктами
        Integer [] list = {23, 56, 12, 78, 11};
        //Саме його викличе.
        Box.printArray(list);
//        Integer a = 17;
//        //Саме його викличе.
//        Box.printArray(a);
    }

    public static void inputTry() throws Exception {
        Scanner scanner = new Scanner(System.in);
        int a = 23, b;
        System.out.println("Вкажіть значення b:");
        try {
            b = scanner.nextInt();
        }
        catch (InputMismatchException imex) {
            //System.out.println("Вкажіть цілі числа. Помилка вводу даних!");
            throw new Exception("Програма працює погано.");
        }
        catch (Exception ex) {
            System.out.printf("Щось пішло не так %s", ex.getMessage());
            return;
        }
        System.out.printf("a/b = %f", a/(double)b);
    }
    //При роботу фукнції main вилітати помилка Exception
    public static void main(String[] args) {
        //template();
//        try {
//            inputTry();
//        }
//        catch (Exception ex) {
//            System.out.printf("Викила помилка %s", ex.getMessage());
//        }
//        finally {
//            System.out.println();
//        }
        int [] mas = {23,56,12,45};
        try {
            System.out.println("mas[4] = " + mas[4]);
            throw new IOException("Сало - це смасно");
        }
        catch(IOException | ArrayIndexOutOfBoundsException ex)
        {
            System.out.println("Виникла помилка вийшли за межі індексу масива");
        }
    }
}