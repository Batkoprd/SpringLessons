package InversionOfControl.Examples;

import InversionOfControl.Configuration.MyConfig_1;
import InversionOfControl.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestJavaConfig {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyConfig_1.class);
        Person person = context.getBean("personBean", Person.class);
        person.callPet();
        System.out.println(person.getSurname()+ " " + person.getAge());

//        Pet cat = context.getBean("catBean", Pet.class);
//        Pet cat1 = context.getBean("catBean", Pet.class);
//        cat.say();

    }
}
