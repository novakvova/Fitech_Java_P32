package example;

import lombok.AllArgsConstructor;
import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;
@Data //автоматично буде get і set та ToString
@AllArgsConstructor
public class Person {
    private String name;
    private int age;
    //Констуктор
    public Person() {
        this.age = 12;
    }
//    public Person(String name, int age) {
//        this.name = name;
//        this.age = age;
//    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    @Override
//    public String toString() {
//        return String.format("Person(name = %s, age = %s)\n", name, age);
//    }

}
