package org.example;

import org.example.entities.CategoryEntity;
import org.example.utils.HibernateHelper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        var session = HibernateHelper.getSession();
        Scanner scanner = new Scanner(System.in);
//        InsertData(scanner);
        ReadData(scanner);
//        DeleteData(scanner);
        UpdateData(scanner);
        ReadData(scanner);
//        session.close();
        System.out.println("Привіт Java!");
    }

    public static void InsertData(Scanner scanner)
    {
        try(var session = HibernateHelper.getSession()) {
            //почати транзацію
            session.beginTransaction();
            System.out.println("Вкажіть назву категорії:");
            String name = scanner.nextLine();
            if(name.isEmpty())
                return;
            var cat = new CategoryEntity(name);
            //context.Categories.Add(cat); // як у C#
            session.persist(cat); //Додати нову категорію
            //завершити транзацію
            session.getTransaction().commit();
            System.out.println("Cat id: " + cat.getId());
        } catch (Exception ex) {
            System.out.printf("Сталася халепа %s", ex.getMessage());
        }
    }

    public static void ReadData(Scanner scanner)
    {
        try(var session = HibernateHelper.getSession()) {
            //почати транзацію
            session.beginTransaction();
            var list = session.createQuery("from CategoryEntity",
                    CategoryEntity.class).getResultList();
            for (var item : list) {
                System.out.printf("%d\t%s\n", item.getId(), item.getName());
            }
            //завершити транзацію
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.printf("Сталася халепа %s", ex.getMessage());
        }
    }

    public static void DeleteData(Scanner scanner)
    {
        try(var session = HibernateHelper.getSession()) {
            //почати транзацію
            session.beginTransaction();
            System.out.println("Вкажіть id запису: ");
            int id =  scanner.nextInt();
            var entity = session.find(CategoryEntity.class, id);
            if (entity != null)
                session.remove(entity);
            //завершити транзацію
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.printf("Сталася халепа %s", ex.getMessage());
        }
    }

    public static void UpdateData(Scanner scanner)
    {
        try(var session = HibernateHelper.getSession()) {
            //почати транзацію
            session.beginTransaction();
            System.out.println("Вкажіть id запису: ");
//            int id = scanner.nextInt();
//            scanner.nextLine(); // очищаємо буфер від \n
            int id = Integer.parseInt(scanner.nextLine());

            var entity = session.find(CategoryEntity.class, id);
            if (entity != null) {
                System.out.println("Вкажіть нову назву: ");
                String name =  scanner.nextLine();
                entity.setName(name);
                session.merge(entity); //Оновити зміни
            }
            //завершити транзацію
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.printf("Сталася халепа %s", ex.getMessage());
        }
    }

}