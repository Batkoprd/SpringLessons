package HibernateLessons;

import HibernateLessons.Entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class MainApp3 {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
//            List<Employee> employees = session.createQuery("from Employee")
//                            .getResultList();

            List<Employee> employees = session.createQuery("from Employee_OneToOne_UNI " +
                            "where name =  'Oleg' AND salary > 700")
                    .getResultList();
            for (Employee e : employees) {
                System.out.println(e);
            }

            session.getTransaction().commit();
            System.out.println("Done");
        } finally {
            factory.close();
        }
    }
}

