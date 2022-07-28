package HibernateLessons.OneToManyBi;

import HibernateLessons.OneToManyBi.Entity.Department_OneToMany_BI;
import HibernateLessons.OneToManyBi.Entity.Employee_OneToMany_BI;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class  OneToMany_BI_App {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee_OneToMany_BI.class)
                .addAnnotatedClass(Department_OneToMany_BI.class)
                .buildSessionFactory();
        Session session = null;

        try {
            session = factory.getCurrentSession();
            addEmployeesToDepartment(session);
//            infoByDepartment(session, 3);
//            infoByEmployee(session,3);
//            deleteEmployee(session, 1);

        } finally {
            session.close();
            factory.close();
        }
    }

    public static void addEmployeesToDepartment(Session session) {
        Department_OneToMany_BI department = new Department_OneToMany_BI("Sales", 1300, 800);
        Employee_OneToMany_BI emp1 = new Employee_OneToMany_BI("John", "Johnson", 800);
        Employee_OneToMany_BI emp2 = new Employee_OneToMany_BI("Elena", "Smirnova", 1000);
        Employee_OneToMany_BI emp3 = new Employee_OneToMany_BI("Anton", "Antonov", 900);
        department.addEmployeeToDepartment(emp1);
        department.addEmployeeToDepartment(emp2);
        department.addEmployeeToDepartment(emp3);

        System.out.println("------------------------------" +
                "\n Заполняем таблицы. Создаем department и добавляем в него employees." +
                "\n------------------------------");

        session.beginTransaction();
        session.persist(department);

        session.getTransaction().commit();
        System.out.println("!!!Done!!!");
    }

    public static void infoByDepartment(Session session, int id) {
        session.beginTransaction();
        System.out.println("Get department: ");
        Department_OneToMany_BI department = session.get(Department_OneToMany_BI.class, id); //когда мы


        System.out.println("Show department: ");
        System.out.println(department);
        System.out.println("Подгрузим наших работников, чтобы информация хранилась и ее не требовалось подгружать.");
        department.getEmps().get(0);

        System.out.println("Show emps of the department before commit: ");
//      При FetchType.EAGER Hibernate один раз делает SELECT всего и ему не нужно потом получать информацию о работниках
        System.out.println(department.getEmps() );
//      При FetchType.LAZY Hibernate делает два селекта, сначала выбирает департамент, а перед тем как вывести работников выбирает работников и выводит их на экран
        session.getTransaction().commit();
        System.out.println("Show emps of the department after commit: "); //Не сработает, если при FetchType.LAZY и мы не подгружали работников до коммита будет LazyInitializationException, при FetchType.EAGER сработает всегда
        System.out.println(department.getEmps() );
        System.out.println("!!!Done!!!");
    }

    public static void infoByEmployee(Session session, int id) {

        session.beginTransaction();
        Employee_OneToMany_BI employee = session.get(Employee_OneToMany_BI.class, id);


        System.out.println("------------------------------" +
                "\n Получаем employee и по нему выводим department." +
                "\n------------------------------");

        //Вывели департамент по сотруднику
        System.out.println(employee);
        System.out.println(employee.getDepartment());

        session.getTransaction().commit();
        System.out.println("!!!Done!!!");
    }

    public static void deleteEmployee(Session session, int id) {
        session.beginTransaction();

        Employee_OneToMany_BI employee = session.get(Employee_OneToMany_BI.class, id);

        System.out.println("------------------------------" +
                "\n Удаляем employee." +
                "\n------------------------------");

        session.remove(employee);
        // Если CascadeType.ALL, то когда мы удаляем одного работника, то он удаляет и его департамент,
        // а за ним удаляются все остальные работники этого департамента
        session.getTransaction().commit();
        System.out.println("!!!Done!!!");
    }
}


