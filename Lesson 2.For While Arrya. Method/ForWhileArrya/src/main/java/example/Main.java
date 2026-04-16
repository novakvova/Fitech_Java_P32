package example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Привіт Патрік!");
        //Типи даних: Ті самі, що у C#
        //boolean, String
        //decimal - Немає //        double a = 12.4;
        //Умовні оператори - так само, як у C# - Різниці немає.
        //Оператори циклу
        //while, do while, for
        // У С# foreach а в Java for in
        String[] list = {"12", "34", "56", "55"};
        System.out.println("List a");
        for (var a : list) {
            System.out.println(a);
//            a = "Сало";
        }
        System.out.println("Update item");
        for (var a : list)
        {
            System.out.println("a = " + a);
        }
        for(int i = 0; i < list.length; i++)
            System.out.printf("%s\t", list[i]);
        System.out.println();
        // Логічні оператори - &&, ||
        String str = "Цей рядок!";

        System.out.printf("%s\n",str.toLowerCase());

        Person peter = new Person();
        peter.setName("Іван Петрович");
//        System.out.println(peter.getName());
        System.out.println(peter);

        Person ivanka = new Person("Ольга Миколаївна", 35);
        System.out.println(ivanka);
    }
}
