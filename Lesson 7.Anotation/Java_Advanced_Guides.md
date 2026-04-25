# Java Advanced Features: Анотації, Анонімні класи, Lambda вирази та Stream API

## 1. АНОТАЦІЇ (Annotations)

### Що таке анотації?
Анотації - це форма метаданих, які надають інформацію про програму, але не впливають безпосередньо на код програми. Вони позначаються символом `@`.

### Вбудовані анотації

#### @Override
Вказує, що метод переписує метод суперкласу.

```java
class Parent {
    public void display() {
        System.out.println("Parent display");
    }
}

class Child extends Parent {
    @Override
    public void display() {
        System.out.println("Child display");
    }
}
```

**Переваги:**
- Компілятор перевіряє наявність методу в суперкласі
- Покращує читаність коду

#### @Deprecated
Позначає, що елемент застарів і не повинен використовуватися.

```java
public class LegacyClass {
    @Deprecated
    public void oldMethod() {
        System.out.println("This method is deprecated");
    }
    
    @Deprecated(since = "2.0", forRemoval = true)
    public void veryOldMethod() {
        // Буде видалено в майбутній версії
    }
}
```

#### @SuppressWarnings
Інформує компілятор про пригнічення певних попереджень.

```java
@SuppressWarnings("unchecked")
public void useRawTypes() {
    List list = new ArrayList(); // без попереджень
    list.add("test");
}

@SuppressWarnings({"unchecked", "deprecation"})
public void multipleWarnings() {
    // Пригнічує кілька типів попереджень
}
```

#### @FunctionalInterface
Позначає інтерфейс як функціональний (має один абстрактний метод).

```java
@FunctionalInterface
public interface Calculator {
    int calculate(int a, int b);
    
    // Можливі статичні та default методи
    static void printResult(int result) {
        System.out.println("Result: " + result);
    }
}
```

### Власні анотації

#### Створення власної анотації

```java
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME) // доступна в runtime
@Target(ElementType.METHOD)          // застосовується до методів
public @interface LogExecution {
    String value() default "Method executed";
}
```

#### Параметри @interface

```java
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Author {
    String name();                          // обов'язковий параметр
    String date() default "Unknown";        // параметр зі значенням за замовчуванням
    String[] tags() default {};             // масив параметрів
}

// Використання
@Author(name = "John Doe", date = "2024-01-15", tags = {"important", "urgent"})
public class MyClass {
    // ...
}
```

#### Meta-анотації

```java
@Retention(RetentionPolicy.RUNTIME)  // коли анотація доступна
@Target(ElementType.METHOD)           // де можна використовувати
@Documented                           // включати в JavaDoc
@Inherited                            // спадкується підкласами
public @interface MyAnnotation {
    // ...
}
```

**RetentionPolicy значення:**
- `SOURCE` - доступна тільки в вихідному коді
- `CLASS` - доступна в .class файлі (за замовчуванням)
- `RUNTIME` - доступна під час виконання

#### Обробка анотацій в runtime

```java
import java.lang.reflect.*;

public class AnnotationProcessor {
    public static void processAnnotations(Object obj) {
        Class<?> clazz = obj.getClass();
        
        // Перевіряємо анотації класу
        if (clazz.isAnnotationPresent(Author.class)) {
            Author author = clazz.getAnnotation(Author.class);
            System.out.println("Author: " + author.name());
            System.out.println("Date: " + author.date());
        }
        
        // Перевіряємо анотації методів
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(LogExecution.class)) {
                LogExecution log = method.getAnnotation(LogExecution.class);
                System.out.println("Method: " + method.getName());
                System.out.println("Log: " + log.value());
            }
        }
    }
}
```

---

## 2. АНОНІМНІ КЛАСИ (Anonymous Classes)

### Що таке анонімні класи?
Це класи без імені, які оголошуються та інстанціюються одночасно. Часто використовуються для реалізації інтерфейсів.

### Синтаксис

```java
new SuperclassOrInterface() {
    // тіло класу
};
```

### Приклади

#### Реалізація інтерфейсу

```java
// Визначення інтерфейсу
interface Greeting {
    void greet(String name);
}

// Анонімна реалізація
Greeting greeting = new Greeting() {
    @Override
    public void greet(String name) {
        System.out.println("Hello, " + name + "!");
    }
};

greeting.greet("Alice");  // Output: Hello, Alice!
```

#### Обробка подій (GUI)

```java
import javax.swing.*;
import java.awt.event.*;

JButton button = new JButton("Click me");
button.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Button clicked!");
    }
});
```

#### Потоки (Threads)

```java
// Стара версія з анонімним класом
Thread thread = new Thread(new Runnable() {
    @Override
    public void run() {
        System.out.println("Thread is running");
    }
});
thread.start();
```

#### Callback функції

```java
class DataProcessor {
    interface OnProcessComplete {
        void onSuccess(String result);
        void onError(Exception e);
    }
    
    public void processData(String data, OnProcessComplete callback) {
        try {
            // обробка даних
            String result = data.toUpperCase();
            callback.onSuccess(result);
        } catch (Exception e) {
            callback.onError(e);
        }
    }
}

// Використання
DataProcessor processor = new DataProcessor();
processor.processData("hello", new DataProcessor.OnProcessComplete() {
    @Override
    public void onSuccess(String result) {
        System.out.println("Success: " + result);
    }
    
    @Override
    public void onError(Exception e) {
        System.err.println("Error: " + e.getMessage());
    }
});
```

### Переваги та недоліки

**Переваги:**
- Розташування коду поблизу місця використання
- Підходить для коротких реалізацій

**Недоліки:**
- Важчитаються для розуміння
- Синтаксично громіздкі
- Займають багато місця в коді

---

## 3. LAMBDA ВИРАЗИ (Lambda Expressions)

### Що таке lambda вирази?
Lambda вирази - це компактна синтаксис для реалізації функціональних інтерфейсів (інтерфейси з одним методом).

### Синтаксис

```
(параметри) -> вираз

(параметри) -> { 
    // блок коду
}
```

### Приклади

#### Базові приклади

```java
// 1. Без параметрів
Runnable r = () -> System.out.println("Hello");
r.run();  // Output: Hello

// 2. З одним параметром
interface Square {
    int calculate(int x);
}
Square square = x -> x * x;
System.out.println(square.calculate(5));  // Output: 25

// 3. З кількома параметрами
interface Add {
    int add(int a, int b);
}
Add add = (a, b) -> a + b;
System.out.println(add.add(3, 4));  // Output: 7

// 4. З явною типізацією
Add add2 = (int a, int b) -> a + b;

// 5. Блок коду
Add add3 = (a, b) -> {
    int result = a + b;
    System.out.println("Sum: " + result);
    return result;
};
```

#### Замість анонімних класів

```java
// ДО (анонімний клас)
button.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Button clicked!");
    }
});

// ПІСЛЯ (lambda)
button.addActionListener(e -> System.out.println("Button clicked!"));

// ДО (потік)
Thread thread = new Thread(new Runnable() {
    @Override
    public void run() {
        System.out.println("Thread running");
    }
});
thread.start();

// ПІСЛЯ (lambda)
new Thread(() -> System.out.println("Thread running")).start();
```

#### Сортування з lambda

```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

// ДО
Collections.sort(names, new Comparator<String>() {
    @Override
    public int compare(String a, String b) {
        return a.compareTo(b);
    }
});

// ПІСЛЯ
Collections.sort(names, (a, b) -> a.compareTo(b));

// Або за довжиною
names.sort((a, b) -> Integer.compare(a.length(), b.length()));
```

### Функціональні інтерфейси java.util.function

```java
import java.util.function.*;

// Consumer<T> - приймає аргумент, нічого не повертає
Consumer<String> print = s -> System.out.println(s);
print.accept("Hello");

// Supplier<T> - нічого не приймає, повертає значення
Supplier<Double> random = () -> Math.random();
System.out.println(random.get());

// Function<T, R> - трансформує один тип в інший
Function<String, Integer> stringToInt = s -> Integer.parseInt(s);
System.out.println(stringToInt.apply("123"));

// Predicate<T> - повертає boolean
Predicate<Integer> isEven = n -> n % 2 == 0;
System.out.println(isEven.test(4));  // true

// BinaryOperator<T> - приймає два аргументи одного типу
BinaryOperator<Integer> multiply = (a, b) -> a * b;
System.out.println(multiply.apply(3, 4));  // 12
```

### Область видимості (Scope)

```java
public void demonstrateScope() {
    int localVar = 10;  // effectively final
    String text = "Hello";  // effectively final
    
    Consumer<Integer> consumer = num -> {
        System.out.println(localVar + num);  // можна використовувати
        System.out.println(text);            // можна використовувати
    };
    
    // localVar = 20;  // ПОМИЛКА! Не можна змінювати
}
```

**Важливо:** Змінні з зовнішнього контексту повинні бути `effectively final` (не змінюються після ініціалізації).

---

## 4. STREAM API

### Що таке Stream API?
Stream API надає функціональний підхід до обробки колекцій даних. Дозволяє писати код, як послідовність операцій фільтрування, трансформації та агрегації.

### Основні концепції

```
Джерело даних → Проміжні операції → Термінальна операція → Результат
```

### Створення Stream

```java
import java.util.stream.*;
import java.util.*;

// 1. З колекції
List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
Stream<Integer> stream1 = list.stream();

// 2. З масиву
Stream<Integer> stream2 = Arrays.stream(new Integer[]{1, 2, 3});

// 3. Прямо зі значень
Stream<Integer> stream3 = Stream.of(1, 2, 3, 4, 5);

// 4. Порожній stream
Stream<Integer> emptyStream = Stream.empty();

// 5. Генерування значень
Stream<Integer> generated = Stream.generate(() -> 5).limit(3);

// 6. Послідовність
Stream<Integer> sequential = Stream.iterate(0, n -> n + 1).limit(5);
```

### Проміжні операції (Intermediate Operations)

#### filter()
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

List<Integer> evenNumbers = numbers.stream()
    .filter(n -> n % 2 == 0)
    .collect(Collectors.toList());

System.out.println(evenNumbers);  // [2, 4, 6]
```

#### map()
```java
List<String> names = Arrays.asList("alice", "bob", "charlie");

List<String> uppercase = names.stream()
    .map(String::toUpperCase)
    .collect(Collectors.toList());

System.out.println(uppercase);  // [ALICE, BOB, CHARLIE]

// Трансформація типів
List<Integer> lengths = names.stream()
    .map(String::length)
    .collect(Collectors.toList());

System.out.println(lengths);  // [5, 3, 7]
```

#### flatMap()
```java
List<List<Integer>> nestedList = Arrays.asList(
    Arrays.asList(1, 2),
    Arrays.asList(3, 4),
    Arrays.asList(5, 6)
);

List<Integer> flattened = nestedList.stream()
    .flatMap(List::stream)
    .collect(Collectors.toList());

System.out.println(flattened);  // [1, 2, 3, 4, 5, 6]
```

#### distinct()
```java
List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 3, 3, 4);

List<Integer> unique = numbers.stream()
    .distinct()
    .collect(Collectors.toList());

System.out.println(unique);  // [1, 2, 3, 4]
```

#### sorted()
```java
List<Integer> numbers = Arrays.asList(5, 2, 8, 1, 9);

// За замовчуванням (у порядку зростання)
List<Integer> sorted1 = numbers.stream()
    .sorted()
    .collect(Collectors.toList());

// За спаданням
List<Integer> sorted2 = numbers.stream()
    .sorted((a, b) -> b.compareTo(a))
    .collect(Collectors.toList());

// Для об'єктів
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
List<String> sorted3 = names.stream()
    .sorted(String::compareTo)
    .collect(Collectors.toList());
```

#### peek()
```java
// Для debug - виводить значення без змін
List<Integer> result = Arrays.asList(1, 2, 3, 4, 5)
    .stream()
    .filter(n -> n > 2)
    .peek(n -> System.out.println("Number: " + n))
    .map(n -> n * 2)
    .peek(n -> System.out.println("After map: " + n))
    .collect(Collectors.toList());
```

#### limit() та skip()
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

// Перші 5 елементів
List<Integer> first5 = numbers.stream()
    .limit(5)
    .collect(Collectors.toList());

// Пропустити перші 3, взяти наступні 4
List<Integer> middle = numbers.stream()
    .skip(3)
    .limit(4)
    .collect(Collectors.toList());
```

### Термінальні операції (Terminal Operations)

#### collect()
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

// У List
List<Integer> list = numbers.stream()
    .filter(n -> n > 2)
    .collect(Collectors.toList());

// У Set
Set<Integer> set = numbers.stream()
    .collect(Collectors.toSet());

// У Map
List<String> names = Arrays.asList("alice", "bob", "charlie");
Map<String, Integer> map = names.stream()
    .collect(Collectors.toMap(
        s -> s,
        String::length
    ));
    // {alice=5, bob=3, charlie=7}

// У String
String joined = names.stream()
    .collect(Collectors.joining(", "));
    // "alice, bob, charlie"

// Групування
Map<Integer, List<Integer>> grouped = numbers.stream()
    .collect(Collectors.groupingBy(n -> n % 2));
    // {0=[2, 4], 1=[1, 3, 5]}
```

#### forEach()
```java
numbers.stream()
    .forEach(System.out::println);
```

#### reduce()
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

// Сума всіх елементів
int sum = numbers.stream()
    .reduce(0, (a, b) -> a + b);
System.out.println(sum);  // 15

// Альтернативно з Optional
Optional<Integer> sumOptional = numbers.stream()
    .reduce((a, b) -> a + b);
sumOptional.ifPresent(System.out::println);

// Множення
int product = numbers.stream()
    .reduce(1, (a, b) -> a * b);
System.out.println(product);  // 120
```

#### match операції
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

// anyMatch - чи є хоч один елемент що задовольняє умову
boolean hasEven = numbers.stream()
    .anyMatch(n -> n % 2 == 0);
System.out.println(hasEven);  // true

// allMatch - чи всі елементи задовольняють умову
boolean allEven = numbers.stream()
    .allMatch(n -> n % 2 == 0);
System.out.println(allEven);  // false

// noneMatch - чи жоден елемент не задовольняє умову
boolean noNegative = numbers.stream()
    .noneMatch(n -> n < 0);
System.out.println(noNegative);  // true
```

#### find операції
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

// Знайти перший елемент
Optional<Integer> first = numbers.stream()
    .filter(n -> n > 2)
    .findFirst();
System.out.println(first.orElse(-1));  // 3

// Знайти будь-який елемент (корисно для паралельних потоків)
Optional<Integer> any = numbers.stream()
    .filter(n -> n > 2)
    .findAny();
```

#### count()
```java
long count = numbers.stream()
    .filter(n -> n > 2)
    .count();
System.out.println(count);  // 3
```

### Комплексні приклади

#### Приклад 1: Обробка списку студентів

```java
class Student {
    private String name;
    private double gpa;
    private String major;
    
    public Student(String name, double gpa, String major) {
        this.name = name;
        this.gpa = gpa;
        this.major = major;
    }
    
    // Getters...
}

List<Student> students = Arrays.asList(
    new Student("Alice", 3.8, "Computer Science"),
    new Student("Bob", 3.2, "Mathematics"),
    new Student("Charlie", 3.9, "Computer Science"),
    new Student("Diana", 3.5, "Physics")
);

// Знайти студентів CS з GPA > 3.5
List<Student> csStudents = students.stream()
    .filter(s -> s.getMajor().equals("Computer Science"))
    .filter(s -> s.getGpa() > 3.5)
    .sorted((a, b) -> Double.compare(b.getGpa(), a.getGpa()))
    .collect(Collectors.toList());

// Список імен студентів з GPA > 3.7
List<String> topStudents = students.stream()
    .filter(s -> s.getGpa() > 3.7)
    .map(Student::getName)
    .collect(Collectors.toList());

// Групування за спеціальністю
Map<String, List<String>> byMajor = students.stream()
    .collect(Collectors.groupingBy(
        Student::getMajor,
        Collectors.mapping(Student::getName, Collectors.toList())
    ));

// Середня оцінка за спеціальністю
Map<String, Double> avgGpaByMajor = students.stream()
    .collect(Collectors.groupingBy(
        Student::getMajor,
        Collectors.averagingDouble(Student::getGpa)
    ));
```

#### Приклад 2: Обробка рядків

```java
String text = "Java is a powerful programming language";
String[] words = text.split(" ");

// Слова довше ніж 4 символи
List<String> longWords = Arrays.stream(words)
    .filter(word -> word.length() > 4)
    .collect(Collectors.toList());

// Кількість кожної літери (case-insensitive)
Map<String, Long> charCount = Arrays.stream(words)
    .flatMapToInt(String::chars)
    .mapToObj(c -> String.valueOf((char) c).toLowerCase())
    .filter(c -> !c.equals(" "))
    .collect(Collectors.groupingBy(
        Function.identity(),
        Collectors.counting()
    ));

// Найдовше слово
String longest = Arrays.stream(words)
    .max(Comparator.comparingInt(String::length))
    .orElse("");
```

### Паралельні Stream

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

// Звичайний stream (послідовний)
List<Integer> sequential = numbers.stream()
    .map(n -> n * 2)
    .collect(Collectors.toList());

// Паралельний stream (використовує кілька потоків)
List<Integer> parallel = numbers.parallelStream()
    .map(n -> n * 2)
    .collect(Collectors.toList());

// Або явне перетворення
List<Integer> alsoParallel = numbers.stream()
    .parallel()
    .filter(n -> n % 2 == 0)
    .map(n -> n * 2)
    .sequential()  // Можна повернутися до послідовного
    .collect(Collectors.toList());
```

**Примітка про паралельні потоки:**
- Корисні для великих наборів даних
- Додаткові витрати на синхронізацію
- Не гарантують порядок обробки

### Примітивні Stream (IntStream, LongStream, DoubleStream)

```java
// IntStream
int sum = IntStream.range(1, 10)  // 1 до 9
    .sum();
System.out.println(sum);  // 45

// LongStream
LongStream.rangeClosed(1, 5)  // 1 до 5 включно
    .forEach(System.out::println);

// DoubleStream
double average = DoubleStream.of(1.5, 2.5, 3.5)
    .average()
    .orElse(0.0);
System.out.println(average);  // 2.5

// Статистика
IntSummaryStatistics stats = IntStream.range(1, 10)
    .summaryStatistics();
System.out.println("Sum: " + stats.getSum());
System.out.println("Average: " + stats.getAverage());
System.out.println("Min: " + stats.getMin());
System.out.println("Max: " + stats.getMax());
```

### Порядок виконання операцій

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

// Stream ліниве виконання - операції виконуються лише при терміналу
List<Integer> result = numbers.stream()
    .peek(n -> System.out.println("1. Original: " + n))
    .filter(n -> n > 2)
    .peek(n -> System.out.println("2. After filter: " + n))
    .map(n -> n * 2)
    .peek(n -> System.out.println("3. After map: " + n))
    .collect(Collectors.toList());

// Виведе тільки для елементів які пройшли фільтр!
// 1. Original: 3
// 2. After filter: 3
// 3. After map: 6
// 1. Original: 4
// 2. After filter: 4
// 3. After map: 8
// 1. Original: 5
// 2. After filter: 5
// 3. After map: 10
```

---

## ПОЄДНАННЯ: LAMBDA + STREAM + АНОТАЦІЇ

### Практичний приклад

```java
import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.stream.*;

// Власна анотація
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Processable {
    boolean enabled() default true;
}

// Клас з даними
class DataItem {
    private int id;
    private String value;
    private boolean active;
    
    public DataItem(int id, String value, boolean active) {
        this.id = id;
        this.value = value;
        this.active = active;
    }
    
    // Getters...
}

// Клас обробника
public class DataProcessor {
    
    @Processable(enabled = true)
    public List<DataItem> processData(List<DataItem> items) {
        return items.stream()
            .filter(DataItem::isActive)
            .sorted(Comparator.comparingInt(DataItem::getId))
            .collect(Collectors.toList());
    }
    
    @Processable(enabled = false)
    public void oldProcessing(List<DataItem> items) {
        // Старий код
    }
    
    // Отримати всі методи позначені @Processable
    public static List<Method> getProcessableMethods(Object obj) {
        return Arrays.stream(obj.getClass().getDeclaredMethods())
            .filter(m -> m.isAnnotationPresent(Processable.class))
            .filter(m -> m.getAnnotation(Processable.class).enabled())
            .collect(Collectors.toList());
    }
}

// Використання
public class Main {
    public static void main(String[] args) {
        List<DataItem> items = Arrays.asList(
            new DataItem(3, "Item C", true),
            new DataItem(1, "Item A", false),
            new DataItem(2, "Item B", true)
        );
        
        DataProcessor processor = new DataProcessor();
        List<DataItem> processed = processor.processData(items);
        
        // Вивести активні записи
        processed.forEach(item -> 
            System.out.println(item.getId() + ": " + item.getValue())
        );
        
        // Дозволені методи обробки
        DataProcessor.getProcessableMethods(processor)
            .forEach(m -> System.out.println("Can process with: " + m.getName()));
    }
}
```

---

## РЕЗЮМЕ

| Концепція | Версія Java | Основне використання | Синтаксис |
|-----------|------------|----------------------|----------|
| **Анотації** | 5.0+ | Метадані, конфігурація | `@AnnotationName` |
| **Анонімні класи** | 1.1+ | Коротка реалізація інтерфейсу | `new Interface() { }` |
| **Lambda вирази** | 8+ | Функціональне програмування | `(x) -> x * 2` |
| **Stream API** | 8+ | Обробка колекцій функціонально | `list.stream().filter()...` |

---

## НАЙВАЖЛИВІШІ ПОРАДИ

1. **Анотації** використовуйте для метаданих та конфігурації
2. **Анонімні класи** замініть **lambda виразами** для читаємості
3. **Lambda вирази** пишіть для функціональних інтерфейсів
4. **Stream API** використовуйте для обробки колекцій безімперативно
5. Пам'ятайте про **effectively final** змінні в lambda
6. Stream операції ліниві - термінальна операція запускає виконання
7. Для великих наборів даних спробуйте **parallelStream()**

---

*Останнє оновлення: 2024*
