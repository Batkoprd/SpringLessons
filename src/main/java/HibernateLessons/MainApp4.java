package HibernateLessons;

import HibernateLessons.Entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class MainApp4 {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            Employee emp = session.get(Employee.class, 5);
//            emp.setSalary(1500);
//           session.createQuery("update Employee set salary = 2000 " +
//                   "where name = 'Elena'").executeUpdate();

//            session.delete(emp);
            session.createQuery("delete Employee " +
                    "where name = 'Alexander'").executeUpdate();

            session.getTransaction().commit();
            System.out.println("Done");
        } finally {
            factory.close();
        }
    }
}

