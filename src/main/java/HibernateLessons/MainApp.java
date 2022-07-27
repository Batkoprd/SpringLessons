package HibernateLessons;

import HibernateLessons.Entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.lang.Nullable;

import javax.management.Query;
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

//            insertEmployeeIntoDB(session);
//            getEmployeeByID(session, 3);
            getEmployeesByNameAndSalary(session,"Oleg", 700);
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
        String query = "from Employee where name = " + myName  + " AND salary > " + mySalary;
        List<Employee> employees = session.createQuery(query)
                .getResultList();
        for (Employee e : employees) {
            System.out.println(e);
        }
        session.getTransaction().commit();
        System.out.println("Done");
    }
}

