package HibernateLessons.ManyToMany;

import HibernateLessons.ManyToMany.Entity.Child_ManyToMany;
import HibernateLessons.ManyToMany.Entity.Section_ManyToMany;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.JoinTable;
import java.util.Arrays;

public class ManyToMany_App {
    public static void main(String[] args) throws ClassNotFoundException {

        Class<?> joinTable = Thread.currentThread().getContextClassLoader()
                .loadClass(JoinTable.class.getName());

        System.out.println(Arrays.asList(joinTable.getDeclaredMethods()));


        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Child_ManyToMany.class)
                .addAnnotatedClass(Section_ManyToMany.class)
                .buildSessionFactory();

        Session session = null;

        try {
             session = factory.getCurrentSession();

           fillTablesBySavingSection(session);
//           addSectionBySavingChild(session);
//           addSectionBySavingChild2(session);
//           getInfoBySession(session, 5);
//           getInfoByChild(session, 7);
//           deleteChild(session, 1);
//           deleteSection(session, 15);


        } finally {
            session.close();
            factory.close();

        }
    }

    public static void fillTablesBySavingSection(Session session) {

        Section_ManyToMany section1 = new Section_ManyToMany("football");
        Child_ManyToMany child1 = new Child_ManyToMany("John", 5);
        Child_ManyToMany child2 = new Child_ManyToMany("Masha", 7);
        Child_ManyToMany child3 = new Child_ManyToMany("Vasya", 6);

        Section_ManyToMany section2 = new Section_ManyToMany("Dance");
        Child_ManyToMany child4 = new Child_ManyToMany("Ivan", 5);
        Child_ManyToMany child5 = new Child_ManyToMany("Bob", 7);
        Child_ManyToMany child6 = new Child_ManyToMany("Katya", 6);

        // Если у нас не CascadeType.ALL, то детей добавлять в секцию нужно после начала транзакции и сохранения секций и сохранять каждого ребенка
        // по отдельности методом session.save(child1) и тд, когда мы использовали CascadeType.ALL в него входили все операции для Cascade, чтобы не было каскадного удаления
        // мы оставили 4 CascadeType. Мы использовали CascadeType из пакета javax.persistence, CascadeType из пакета hibernate именнт намного больше
        // операций, когда мы используем CascadeType из javax.persistence и вместо CascadeType.ALL мы выбираем определенные каскадтайпы
        // то у нас нет возможности выбрать CascadeType.SAVE, потому что в javax.persistence нет такой операции, поэтому без CascadeType.ALL
        // каскадного сохранения не будет. Но мы можем вместо session.save() написать session.persist() и все прекрасно сохранится.

        section1.addChildToSection(child1);
        section1.addChildToSection(child2);
        section1.addChildToSection(child3);
        section2.addChildToSection(child4);
        section2.addChildToSection(child5);
        section2.addChildToSection(child6);

        session.beginTransaction();

//        session.save(section1);
//        session.save(section2);
        session.persist(section1);
        session.persist(section2);

        session.getTransaction().commit();

        System.out.println("!!!Done!!!");
    }

    public static void addSectionBySavingChild(Session session) {
        Section_ManyToMany section1 = new Section_ManyToMany("volleyball");
        Section_ManyToMany section2 = new Section_ManyToMany("chess");
        Section_ManyToMany section3 = new Section_ManyToMany("math");

        Child_ManyToMany child1 = new Child_ManyToMany("Igor", 10);

        child1.addSectionToChild(section1);
        child1.addSectionToChild(section2);
        child1.addSectionToChild(section3);

        session.beginTransaction();
        session.save(child1);
        session.getTransaction().commit();

        System.out.println("!!!Done!!!");
    }

    public static void addSectionBySavingChild2(Session session) {

        Section_ManyToMany section1 = new Section_ManyToMany("Box");
        Child_ManyToMany child1 = new Child_ManyToMany("Bob1", 5);
        Child_ManyToMany child2 = new Child_ManyToMany("Bob2", 7);
        Child_ManyToMany child3 = new Child_ManyToMany("Bob3", 6);

//        section1.addChildToSection(child1);
//        section1.addChildToSection(child2);
//        section1.addChildToSection(child3);

        child1.addSectionToChild(section1);
        child2.addSectionToChild(section1);
        child3.addSectionToChild(section1);

        session.beginTransaction();

        session.save(child1);
        session.save(child2);
        session.save(child3);

        session.getTransaction().commit();
        System.out.println("!!!Done!!!");
    }

    public static void getInfoBySession(Session session, int id) {

        session.beginTransaction();
        Section_ManyToMany section = session.get(Section_ManyToMany.class, id);

        System.out.println(section);
        System.out.println(section.getChildList());
        System.out.println("--------------------------------------");

        session.getTransaction().commit();
        System.out.println("!!!Done!!!");
    }

    public static void getInfoByChild(Session session, int id) {
        session.beginTransaction();
        Child_ManyToMany child = session.get(Child_ManyToMany.class, id);

        System.out.println(child);
        System.out.println(child.getSections());
        System.out.println("--------------------------------------");

        session.getTransaction().commit();
        System.out.println("!!!Done!!!");
    }

    public static void deleteChild(Session session, int id) {
        session.beginTransaction();

        Child_ManyToMany child = session.get(Child_ManyToMany.class, id);
        session.delete(child);

        session.getTransaction().commit();
    }

    public static void deleteSection(Session session, int id) {
        session.beginTransaction();

        Section_ManyToMany section = session.get(Section_ManyToMany.class, id);

        session.delete(section);
        session.getTransaction().commit();

        System.out.println("!!!Done!!!");
    }
}
