package HibernateLessons.OneToOneUni;

import HibernateLessons.OneToOneUni.Entity.Detail_OneToOne_UNI;
import HibernateLessons.OneToOneUni.Entity.Employee_OneToOne_UNI;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class OneToOne_UNI_App {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee_OneToOne_UNI.class)
                .addAnnotatedClass(Detail_OneToOne_UNI.class)
                .buildSessionFactory();

        Session session = null; //Session объявлен тут, чтобы можно было в finally блоке закрыть ее методом close().

        try {
            session = factory.getCurrentSession();
            addEmployees(session);
//            deleteEmployee(session, 10);


        } finally {
            session.close();
            factory.close();
        }
    }

    public static void addEmployees(Session session) {
        Employee_OneToOne_UNI emp1 = new Employee_OneToOne_UNI("John", "Johnson", "IT", 500);
        Detail_OneToOne_UNI detail1 = new Detail_OneToOne_UNI("Praha", "123456789", "johnson@gmail.com");
        Employee_OneToOne_UNI emp2 = new Employee_OneToOne_UNI("Oleg", "Smirnov", "Sales", 600);
        Detail_OneToOne_UNI detail2 = new Detail_OneToOne_UNI("Moscow", "987654321", "smirnov@gmail.com");

        System.out.println("------------------------------" +
                "\n Назначаем детали работнику" +
                "\n------------------------------");
        emp1.setEmpDetail(detail1); // назначаем детали работнику
        emp2.setEmpDetail(detail2);
        session.beginTransaction();

        System.out.println("------------------------------" +
                "\n Сохраняем работника и, благодаря каскаду, сохраняются его детали." +
                "\n------------------------------");
        session.save(emp1);
        session.save(emp2);

        session.getTransaction().commit();
        System.out.println("!!!Done!!!");
    }

    public static void deleteEmployee(Session session, int id) {
            session.beginTransaction();
            Employee_OneToOne_UNI emp = session.get(Employee_OneToOne_UNI.class, id); //получаем работника с id
            System.out.println("Details работника, которого хотим удалить" + emp.getEmpDetail());
        System.out.println("------------------------------" +
                "\n Удаляем работника, благодаря каскаду удалятся и его детали." +
                "\n------------------------------");
            session.delete(emp);
            session.getTransaction().commit();
            System.out.println("!!!Done!!!");
    }
}

/*
CREATE TABLE hibernate_example_db.details_OneToOne (
  id int NOT NULL AUTO_INCREMENT,
  city varchar(15),
  phone_number varchar(25),
  email varchar(30), PRIMARY KEY (id)
);

CREATE TABLE hibernate_example_db.employees_OneToOne (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(15),
  surname varchar(25),
  department varchar(20), salary int, details_id int
,  PRIMARY KEY (id)
, FOREIGN KEY (details_id) REFERENCES hibernate_example_db.details_OneToOne(id));
 */

/*
Пришлось поменять javax.persistence.* на jakarta в entity, иначе было исключение  Unable to locate persister.
 */