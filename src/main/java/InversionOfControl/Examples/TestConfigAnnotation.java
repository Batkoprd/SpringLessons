package InversionOfControl.Examples;

import InversionOfControl.Animals.Cat;
import InversionOfControl.PersonAnno;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestConfigAnnotation {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext3.xml");

        Cat myCat = context.getBean("catBean", Cat.class);
//        myCat.say();

        PersonAnno person = context.getBean("personAnnoBean", PersonAnno.class);
        person.callPet();
        System.out.println(person.getSurname() + " " + person.getAge());

        context.close();
    }
}
