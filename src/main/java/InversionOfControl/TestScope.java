package InversionOfControl;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestScope {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext3.xml");

        Dog myDog = context.getBean("dogBean", Dog.class);
        myDog.setName("Belka");
        Dog yourDog = context.getBean("dogBean", Dog.class);
        yourDog.setName("Strelka");

        System.out.println("Переменные ссылаются на один и тот же объект " +
                (myDog == yourDog));
        System.out.println(myDog);
        System.out.println(yourDog);
        myDog.say();

        context.close();

    }
}
