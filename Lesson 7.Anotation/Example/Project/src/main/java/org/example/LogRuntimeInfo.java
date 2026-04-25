package org.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//Цей default метод буде виконувати при ініціалізації додатку.
//По суті запуску вашої програми
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogRuntimeInfo {
    String value() default "Виконання методу";
}
