package org.example.myclasses;

//Аналогія Tamplate у C# - java Generic
public class Box<T> {
    private T content;

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
    //Якщо є статичний метод в класі, то він може мати
    //свій власний шаблон - тип з яким буде працювати
    public static <MyType> void printArray(MyType[] elements)
    {
        for (var item : elements)
            System.out.println(item);
    }
}
