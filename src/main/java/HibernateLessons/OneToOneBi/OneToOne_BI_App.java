package HibernateLessons.OneToOneBi;

import HibernateLessons.OneToOneBi.Entity.Detail_OneToOne_BI;
import HibernateLessons.OneToOneBi.Entity.Employee_OneToOne_BI;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/*
В Bi-directional отношениях с помощью аннотации @OneToOne и mappedBy
мы показываем Hibernate, где нужно искать связь между классами
 */
//Bi-directional -это отношения, когда обе стороны знают друг о друге

public class OneToOne_BI_App {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee_OneToOne_BI.class)
                .addAnnotatedClass(Detail_OneToOne_BI.class)
                .buildSessionFactory();

        Session session = null;

        try {
            session = factory.getCurrentSession();
//            addEmployeeByDetail(session);
//            getInfoByDetail(session, 1);
            tearEmpDetailConnection(session, 1);




        } finally {
            session.close();
            factory.close();
        }
    }

    public static void addEmployeeByDetail(Session session) {
            Employee_OneToOne_BI emp = new Employee_OneToOne_BI("Nikolay", "Ivanov", "HR", 850);
            Detail_OneToOne_BI detail = new Detail_OneToOne_BI("New-York", "56531239", "nikolay@gmail.com");

            // теперь details id не будет null

        System.out.println("------------------------------" +
                "\n Назначаем детали работнику и работника деталям." +
                "\n------------------------------");
            emp.setEmpDetail(detail); //нужно чтобы столбец details_id не было null
            detail.setEmployee(emp);

            session.beginTransaction();
            //для Details нужен CascadeType.ALL
        System.out.println("------------------------------" +
                "\n Сохраняем детали, сохранится и работник за счет двусторонней связи." +
                "\n------------------------------");
            session.save(detail);

            session.getTransaction().commit();
            System.out.println("!!!Done!!!");
    }

    public static void getInfoByDetail(Session session, int id) {
        System.out.println("Получим информацию с помощью Detail");
        session.beginTransaction();

        Detail_OneToOne_BI detail = session.get(Detail_OneToOne_BI.class, id); // получаем detail с id 4
        Employee_OneToOne_BI employee = detail.getEmployee();
        System.out.println("------------------------------" +
                "\n Выведем информацию о Employee по его Details." +
                "\n------------------------------");
        System.out.println(employee);

        session.getTransaction().commit();
        System.out.println("!!!Done!!!");
    }

    public static void tearEmpDetailConnection(Session session, int id) {
        session.beginTransaction();
        Detail_OneToOne_BI detail = session.get(Detail_OneToOne_BI.class, id); // получаем detail с id

        //Так как ForeignKey всегда должен ссылаться на PrimaryKey, то нужно разорвать связь между классами, тк
        //метод выдаст исключение, потому что ForeignKey начнет ссылаться на несуществующую строку
        detail.getEmployee().setEmpDetail(null); // с помощью деталей мы получаем работника и говорим что у этого работника больше нет details после чего мы можем удалить детали о работнике, а работник останется в бд
        session.delete(detail);
        /*
        Если в Details CascadeType.ALL, то при выполнении этого метода удалятся и детали и работник.
        Чтобы удалились только детали нужен {CascadeType.PERSIST, CascadeType.REFRESH}
         */
        session.getTransaction().commit();
        System.out.println("!!!Done!!!");
    }
}

