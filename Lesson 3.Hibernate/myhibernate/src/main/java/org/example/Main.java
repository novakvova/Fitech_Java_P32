package org.example;

import org.example.entities.CategoryEntity;
import org.example.utils.HibernateHelper;

public class Main {
    public static void main(String[] args) {
        var session = HibernateHelper.getSession();

        try {
            //почати транзацію
            session.beginTransaction();

            var cat = new CategoryEntity("Ковбасні вироби");
            session.persist(cat); //Додати нову категорію

            //завершити транзацію
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.printf("Сталася халепа %d", ex.getMessage());
        }

        session.close();
        System.out.println("Привіт Java!");
    }
}