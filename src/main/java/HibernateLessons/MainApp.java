package HibernateLessons;

import HibernateLessons.Entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;


public class MainApp {
    public static final String HIBERNATE_CONFIG = "hibernate.cfg.xml";

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure(HIBERNATE_CONFIG)
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();
        Session session = null;
        try {
            session = factory.getCurrentSession();

            insertEmployeeIntoDB(session);
//            getEmployeeByID(session, 3);
//            getEmployeesByNameAndSalary(session,"Oleg", 700);
//            updateEmployeeSalary(session, 6, 2000);
//            deleteEmployeeFromDB(session, 3);

        } finally {
            session.close();
            factory.close();
        }
    }

    public static void insertEmployeeIntoDB(Session session) {

            Employee emp1 = new Employee("Alexander", "Kuznetsov", "Sales", 800);
            Employee emp2 = new Employee("Oleg", "Dmitriev", "IT", 900);
            Employee emp3 = new Employee("Elena", "Ivanova", "HR", 1000);

            session.beginTransaction();
            session.save(emp1);
            session.save(emp2);
            session.save(emp3);

            session.getTransaction().commit();

            System.out.println("Employees added to DB.");
    }

    public static void getEmployeeByID(Session session, int id) {
        session.beginTransaction();
        Employee employee = session.get(Employee.class, id);
        session.getTransaction().commit();
        System.out.println(employee);
        System.out.println("Done selecting employee with " + id + " from DB.");
    }

    public static void getEmployeesByNameAndSalary(Session session, String myName, int mySalary) {

        session.beginTransaction();
        Query query = session.createQuery("from Employee where name = ?1 AND salary > ?2", Employee.class);
        query.setParameter(1, myName);
        query.setParameter(2, mySalary);
        List<Employee> employees = query.getResultList();
        for (Employee e : employees) {
            System.out.println(e);
        }
        session.getTransaction().commit();
        System.out.println("!!!Done!!!");
    }

    public static void updateEmployeeSalary(Session session, int id, int mySalary) {
        session.beginTransaction();
        Query query = session.createQuery("update Employee set salary = ?1 where id = ?2");
        query.setParameter(1, mySalary);
        query.setParameter(2, id);
        query.executeUpdate();

//        Employee emp = session.get(Employee.class, id);
//        emp.setSalary(mySalary);

        session.getTransaction().commit();
        System.out.println("!!!Done!!!");
    }

    public static void deleteEmployeeFromDB(Session session, int id) {
        session.beginTransaction();
        Query query = session.createQuery("delete  Employee where id = ?1");
        query.setParameter(1, id);
        query.executeUpdate();

//        Employee emp = session.get(Employee.class, id);
//        session.delete(emp);


        session.getTransaction().commit();
        System.out.println("!!!Done!!!");
    }
}

/*
CREATE DATABASE  hibernate_example_db;
USE hibernate_example_db;

CREATE TABLE employees (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(15),
  surname varchar(25),
  department varchar(20),
  salary int,
  PRIMARY KEY (id)
);
 */
