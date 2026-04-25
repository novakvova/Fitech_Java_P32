package org.example;

import java.lang.reflect.Method;

public class AnnotationProcessor {
    public static void processAnnotations(Object obj) {
        Class<?> clazz = obj.getClass();

        // Перевіряємо анотації методів
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(LogRuntimeInfo.class)) {
                LogRuntimeInfo log = method.getAnnotation(LogRuntimeInfo.class);
                System.out.println("Method: " + method.getName());
                System.out.println("Log: " + log.value());
            }
        }
    }
}
