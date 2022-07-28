package HibernateLessons.OneToManyUni;


import HibernateLessons.OneToManyUni.Entity.Department_OneToMany_UNI;
import HibernateLessons.OneToManyUni.Entity.Employee_OneToMany_UNI;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class OneToMany_UNI_App {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee_OneToMany_UNI.class)
                .addAnnotatedClass(Department_OneToMany_UNI.class)
                .buildSessionFactory();

        Session session = null;

        try {
            session = factory.getCurrentSession();
            addEmployeesByDepartment(session);
//            getEmpsByDepartment(session, 1);
//            deleteEmployee(session, 1);
//            deleteDepartment(session, 1);

        } finally {
            session.close();
            factory.close();
        }
    }
    public static void addEmployeesByDepartment(Session session) {
        Department_OneToMany_UNI department = new Department_OneToMany_UNI("HR", 500, 1500);
        Employee_OneToMany_UNI emp1 = new Employee_OneToMany_UNI("Oleg", "Olegov", 800);
        Employee_OneToMany_UNI emp2 = new Employee_OneToMany_UNI("Andrei", "Andreev", 1000);
        department.addEmployeeToDepartment(emp1);
        department.addEmployeeToDepartment(emp2);

        System.out.println("------------------------------" +
                "\n Создаем department и добавляем в него employees, сохраняем department и сохранятся employees." +
                "\n------------------------------");
        // Благодаря Uni-directional связи и CascadeType.ALL при сохранении департамента сохранятся и работники
        session.beginTransaction();
        session.persist(department);

        session.getTransaction().commit();
        System.out.println("!!!Done!!!");
    }

    public static void getEmpsByDepartment(Session session, int id) {

        session.beginTransaction();

        Department_OneToMany_UNI department = session.get(Department_OneToMany_UNI.class, id);
        System.out.println("------------------------------" +
                "\n Выберем department и выведем информацию о департаменте и employees департамента." +
                "\n------------------------------");

        System.out.println("Департамент: " + department);
        System.out.println("Работники департамента: " + department.getEmps() );

        session.getTransaction().commit();
        System.out.println("!!!Done!!!");
    }

    public static void getDepByEmployee(Session session, int id) {

        session.beginTransaction();
        Employee_OneToMany_UNI employee = session.get(Employee_OneToMany_UNI.class, id);

        System.out.println("------------------------------" +
                "\n Выберем Employee и выведем информацию о сотруднике." +
                "\n------------------------------");

        //В Uni-directional связи работник не содержит информацию о департаменте, поэтому мы можем только получить информацию о работнике
        System.out.println(employee);

        session.getTransaction().commit();
        System.out.println("!!!Done!!!");
    }

    public static void deleteEmployee(Session session, int id) {
        session.beginTransaction();
        System.out.println("------------------------------" +
                "\n Выберем работника и удалим его." +
                "\n------------------------------");
        //Из-за Uni-directional связи удалится только работник, а его департамет останется нетронут.
        Employee_OneToMany_UNI employee = session.get(Employee_OneToMany_UNI.class, id);
        System.out.println("Удаляем работника с " + id + ": " + employee);
        session.delete(employee);
        session.getTransaction().commit();
        System.out.println("!!!Done!!!");
    }

    public static void deleteDepartment(Session session, int id) {
        session.beginTransaction();

        System.out.println("------------------------------" +
                "\n Выберем Department и удалим его." +
                "\n------------------------------");
        //Если мы будем удалять департамент, то произойдет каскадное удаление и удалятся все его работники тоже
        Department_OneToMany_UNI department = session.get(Department_OneToMany_UNI.class, id);
        System.out.println("Удаляем department c id " + id + ": " + department);
        session.delete(department);

        session.getTransaction().commit();
        System.out.println("!!!Done!!!");

    }
}

/*
CREATE TABLE hibernate_example_db.departments_OneToMany (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(15),
  max_salary int,
  min_salary int,
  PRIMARY KEY (id)
);

CREATE TABLE hibernate_example_db.employees_OneToMany (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(15),
  surname varchar(25),
  salary int,
  department_id int,
  PRIMARY KEY (id),
  FOREIGN KEY (department_id) REFERENCES hibernate_example_db.departments_OneToMany(id));
 */

