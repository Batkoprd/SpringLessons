package InversionOfControl.Examples;

import InversionOfControl.Animals.Cat;
import InversionOfControl.Animals.Dog;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBeanScope {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext2.xml");

        Dog myDog = context.getBean("myPet", Dog.class);
        myDog.setName("Belka");
        Dog yourDog = context.getBean("myPet", Dog.class);
        yourDog.setName("Strelka");

        System.out.println("Переменные ссылаются на один и тот же объект: " +
                (myDog == yourDog));
        System.out.println(myDog);
        System.out.println(yourDog);

        System.out.println(myDog.getName());
        System.out.println(yourDog.getName());


        Cat myCat = context.getBean("myCat", Cat.class);
        myCat.say();
        Cat yourCat = context.getBean("myCat", Cat.class);
        yourCat.say();

        context.close();

    }
}
