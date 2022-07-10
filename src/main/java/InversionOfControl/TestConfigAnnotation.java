package InversionOfControl;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestConfigAnnotation {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext3.xml");

//        Cat myCat = context.getBean("catBean", Cat.class);
//        myCat.say();

        Person person = context.getBean("personBean", Person.class);
        person.callPet();
        System.out.println(person.getSurname() + " " + person.getAge());

        context.close();
    }
}
