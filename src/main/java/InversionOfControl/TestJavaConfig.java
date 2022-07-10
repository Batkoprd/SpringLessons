package InversionOfControl;

import InversionOfControl.Configuration.MyConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestJavaConfig {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyConfig.class);
        Person person = context.getBean("personBean", Person.class);
        person.callPet();
        System.out.println(person.getSurname()+ " " + person.getAge());

//        Pet cat = context.getBean("catBean", Pet.class);
//        Pet cat1 = context.getBean("catBean", Pet.class);
//        cat.say();

    }
}
