package com.sk.mbc.business.domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class EmployeeTest {
    private static EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("example-unit");

    public static void main(String[] args) {
        try {
            persistEmployees();
            findAllEmployees();
            deleteEmployeeByName();
            findAllEmployees();
            deleteAllEmployees();
            findAllEmployees();
        } finally {
            entityManagerFactory.close();
        }
    }

    public static void persistEmployees() {
        Employee employee1 = Employee.create("Diana", 2000, "IT");
        Employee employee2 = Employee.create("Rose", 3500, "Admin");
        Employee employee3 = Employee.create("Denise", 2500, "Admin");
        Employee employee4 = Employee.create("Mike", 4000, "IT");
        Employee employee5 = Employee.create("Linda", 4500, "Sales");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(employee1);
        em.persist(employee2);
        em.persist(employee3);
        em.persist(employee4);
        em.persist(employee5);
        em.getTransaction().commit();
        em.close();
    }

    private static void findAllEmployees() {
        EntityManager em = entityManagerFactory.createEntityManager();
        System.out.println("-- All employees --");
        Query query = em.createQuery(
                "SELECT e FROM Employee e");
        List<Employee> resultList = query.getResultList();
        System.out.println("Employees count: "+resultList.size());
        resultList.forEach(System.out::println);
        em.close();
    }

    private static void deleteEmployeeByName() {
        System.out.println("-- delete employee by name 'Mike' --");

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("DELETE FROM Employee e WHERE e.name = :employeeName ");
        query.setParameter("employeeName", "Mike");
        int rowsDeleted = query.executeUpdate();
        System.out.println("entities deleted: " + rowsDeleted);
        em.getTransaction().commit();

        em.close();

    }

    private static void deleteAllEmployees() {
        System.out.println("-- delete all employees --");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("DELETE FROM Employee e ");
        int rowsDeleted = query.executeUpdate();
        System.out.println("entities deleted: " + rowsDeleted);
        em.getTransaction().commit();
        em.close();
    }
}