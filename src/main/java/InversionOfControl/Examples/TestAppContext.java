package InversionOfControl.Examples;

import InversionOfControl.Animals.Pet;
import InversionOfControl.Person;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAppContext {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        Pet pet = context.getBean("myPet", Pet.class);

        //Dependency injection
//        Person person = new Person(pet);

        // в appContext pet в конструкторе Person
        Person person1 = context.getBean("myPerson", Person.class);
        person1.callPet();
        System.out.println(person1.getAge() + " " + person1.getSurname());


        context.close();
    }
}
