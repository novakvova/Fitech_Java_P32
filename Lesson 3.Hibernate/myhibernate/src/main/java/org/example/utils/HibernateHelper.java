package org.example.utils;

import org.example.entities.CategoryEntity;
import org.example.entities.ProductEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateHelper {
    public static SessionFactory sessionFactory;

    // цей метод, буде автоматично викликатися
    // Це означає, що якщо ми використовємо клас HibernateHelper
    // Цей метод викликатиметься лише 1 раз.
    static {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                //Якщо файл має стандартну назву hibernate.properties - саме знайду
                //.configure()
                .build();
        try {
            sessionFactory = new MetadataSources(registry)
                    .addAnnotatedClass(CategoryEntity.class)
                    .addAnnotatedClass(ProductEntity.class)
                    .buildMetadata()
                    .buildSessionFactory();
        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }
    public static void shutDown() {
        if(sessionFactory != null)
            sessionFactory.close();
    }
}
