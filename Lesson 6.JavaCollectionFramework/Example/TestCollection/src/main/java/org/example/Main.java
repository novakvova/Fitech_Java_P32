package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void MyArrayList()
    {
        List<String> items = new ArrayList<String>();
        items.add("Яблука");
        items.add("Черешні");
        items.add("Вишні");

        System.out.println("Список фруктів");
        for (var item : items)
            System.out.println(item);

        System.out.println("item 0: "+ items.get(0));

        items.set(1, "Зефір");
        System.out.println("Список фруктів зміна елемента");
        for (var item : items)
            System.out.println(item);
        items.remove(0);
//        items.remove("Зефір");
        System.out.println("Усі козаки: " + items);
    }

    public static void SortArrayList() {
        List<Integer> numbers =
                new ArrayList<>(Arrays.asList(2,6,8,12,5,2,0));
        //Collections.sort(numbers);
        //System.out.println("Сортований  писок "+ numbers);
        numbers.sort(null);
        System.out.println("Сортований  cписок "+ numbers);

//        numbers.sort((a,b) -> b-a);
        Collections.sort(numbers,Collections.reverseOrder());
        System.out.println("Зворотрній порядок  "+ numbers);

    }

    public static void ArrayRemoveItem() {
        List<String> colors = new ArrayList<>(
                Arrays.asList("Зелений","Червоний","Жовтий","Синій","Баклажаний")
        );
//        for (var item : colors) {
//            if(item.equals("Жовтий"))
//                colors.remove(item);
//        }
        Iterator<String> iter = colors.iterator();
        while(iter.hasNext()) { //hasNext() - перевіряє чи є елемент у послідовності
            if(iter.next().equals("Зелений")) //Берез елемент із послідовності
                iter.remove(); //Безпечне видалення елемента
        }
        System.out.println("Список кольорів: "+ colors);

        colors = new ArrayList<>(
                Arrays.asList("Зелений","Жовтий","Червоний","Жовтий","Синій","Баклажаний")
        );
        colors.removeAll(Arrays.asList("Жовтий"));
        System.out.println("Remove All "+ colors);

        colors = new ArrayList<>(
                Arrays.asList("Зелений","Жовтий","Банановий","Червоний",
                        "Жовтий","Синій","Баклажаний")
        );
        colors = colors.stream()
                .filter(c->!c.equals("Банановий"))
                .collect(Collectors.toList());
        System.out.println("Remove stream "+ colors);
    }

    //Dictionary - у C#
    public static void MyHashMap() {
        Map<String, Integer> map = new HashMap<>();
        //Додаємо елементи
        map.put("Іван", 24);
        map.put("Петро", 19);
        map.put("Панас", 12);
        //Тут буде оновляти Панас - стає 18
        map.put("Панас", 18); // 2 рази Панас Бути не можу. Повторне додавання заміняє елемент
        System.out.println("HashMap "+ map);

        System.out.println("Іван його вік "+map.get("Іван"));

        for (var key : map.keySet()) {
            System.out.println(key + " -> " + map.get(key));
        }

        System.out.println("Search key "+map.containsKey("Іван"));
        System.out.println("Search value "+map.containsValue(18));
    }

    public static void main(String[] args) {
        //System.out.println("--Робота з колекція Java--");
//        MyArrayList();
//        SortArrayList();
//        ArrayRemoveItem();
//        MyHashMap();
        //TreeSet - той же сами HashSet - але він завжди сортований
        //Усі елементи, які попадаються у дерево автоматично там зберігаються сортовані
        List<Person> people = Arrays.asList(
                new Person("Семен",12),
                new Person("Павло",28),
                new Person("Їжак",9)
        );
        Collections.sort(people);
        System.out.println("Сортований список " + people);
        //Будемо сортування елементи по імені, передає вказіник на метод
        Collections.sort(people, Comparator.comparing(Person::getName));
        System.out.println("Сортування по імені "+ people);
        Collections.sort(people, Comparator.comparing(Person::getAge).reversed());
        System.out.println("Сортування по вікові спадання "+ people);

//        Queue<Integer> myQueue = new PriorityQueue<>();
//
//        myQueue.add(12);
//        myQueue.add(18);
//        myQueue.add(10);
//        myQueue.add(11);
//
//        System.out.println("Queue "+myQueue);

        Deque<String> myQueue = new ArrayDeque<>();
        myQueue.add("Семен Палій");
        myQueue.add("Артур Волович");
        myQueue.add("Левове Серце");

        System.out.println(myQueue);

        var item = myQueue.pop();
        System.out.println("Видалили елемент: " + item);
        System.out.println("Новий список: " + myQueue);
    }
}