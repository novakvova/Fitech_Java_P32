import java.util.Random;
import java.util.Scanner;

public class Program {

    static void simple1() {
        //Ті сам, що у C№
        int a;
        double v;
        float p;
        boolean b;
        int[] array; // масив
        char ch = 'А'; //unicode - 2 байти

        // C# - Collection - Generic
        String str; //Класи з великої букви
        System.out.println("Вкажіть, як Вас звати:");
        //Даний Scanner - отримує дані із консолі. System.in
        Scanner input = new Scanner(System.in);
        str = input.next(); //Отримуємо рядок Console.ReadLine()
//        System.out.println("Привіт друже " + str);
        //%s - буде підставлятися у дане місце - змінна після коми
        System.out.printf("Привіт друже %s\n", str);
        System.out.print("Скільки Вам рочків: ");
        str = input.next();
        int age = Integer.parseInt(str); // пететворюємо у число
        System.out.printf("Вам %s років\n", age);
    }

    static void simpleIf() {
        // >,<,=>,<=,<!=,==
        System.out.println("Скільки Вам рочків:");
        Scanner scanner = new Scanner(System.in);
        int age = Integer.parseInt(scanner.next());
        if (age >= 23 && age < 25) {
            System.out.println("Ви не можете виїхати за кордон.");
        } else if (age >= 25 && age < 60)
            System.out.println("Вас можуть мобілізувати. У вас є Резерс +");
        else
            System.out.println("Ви можете їхати за кордон :)");
    }

    static void simpleFor() {
        Scanner scanner = new Scanner(System.in);

        String [] names = {
                "Іванка",
                "Мальвіна",
                "Руслана",
                "Галина",
                "Василь"
        };
        //Це foreach - просто так в Java пишеться foreach
        for (String name : names) {
            System.out.println(name);
        }
        Random rand = new Random();
        System.out.println("Вкажіть розмір масиву: ");
        int size = Integer.parseInt(scanner.next());
        int []myArray = new int[size];
        for (int i=0; i<size; i++)
            myArray[i] = random(18, 30);
        for(var item : myArray)
        {
            System.out.printf("%d\t", item);
        }
    }

    static int random(int min, int max)
    {
        Random random = new Random();
        int randomInt = min + random.nextInt(max - min);
        return randomInt;
    }

    public static void main(String[] args) {
//        simple1();
//        simpleIf();
//        simpleFor(); //приклади циклу фор
        //побітова операція XOR
//        int a = 12;// переставити змінні місцями, не викоривуючи 3 змінну
//        int b = 18;
//        System.out.printf("a = %d\tb = %d\n", a, b);
//        a = a ^ b;
//        System.out.printf("a = %d\n", a);
//        b = a ^ b;
//        a = a ^ b;
//        System.out.printf("a = %d\tb = %d", a, b);
        //immutable
        int a = 12;// переставити змінні місцями, не викоривуючи 3 змінну
        int b = 24;
        System.out.printf("a = %d\tb = %d\n", a, b);
        a = a ^ b;
        System.out.printf("a = %d\n", a);
        b = a ^ b;
        a = a ^ b;
        System.out.printf("a = %d\tb = %d", a, b);
    }
}
