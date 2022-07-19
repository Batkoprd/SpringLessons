package InversionOfControl;

import InversionOfControl.Animals.Cat;
import InversionOfControl.Animals.Dog;
import InversionOfControl.Animals.Pet;
import InversionOfControl.Configuration.MyConfig_1;
import InversionOfControl.Configuration.MyConfig_2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
Основные функции, которые выполняет
Spring Container:
•IoC–инверсия управления
Создание и управление объектами
•DI –Dependency Injection
Внедрение зависимостей
IoC–аутсорсинг создания и управления объектами. Т.е. передача программистом прав на создание и управление объектами Spring-у.
DI –аутсорсинг добавления/внедрения зависимостей. DI делает объекты нашего приложения слабо зависимыми друг от друга.
 */
public class MainApp {
    public static void main(String[] args) {
        configurationWithXML();
        System.out.println("\n**************************************\n");
        configurationWithXML_BeanScopeExample_DestroyInitMethods();
        System.out.println("\n**************************************\n");
        configurationXMLandAnnotations();
        System.out.println("\n**************************************\n");
        configurationWithJAVA();

    }

    public static void configurationWithXML(){
        System.out.println("Конфигурация Spring Container с помощью XML файла: \n");
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        Pet pet = context.getBean("myPet", Pet.class);

        //Dependency injection
//        Person person = new Person(pet);
//        System.out.println(person);

        // в appContext pet в конструкторе Person
        Person person1 = context.getBean("myPerson", Person.class);
        person1.callPet();
        System.out.println("Фамилия из myApp.properties: " + person1.getSurname() + "\nИмя из myApp.properties: " + person1.getAge());

        context.close();
    }

    public static void configurationWithXML_BeanScopeExample_DestroyInitMethods(){
        /*
        Scope (область видимости)определяет:
        •жизненный цикл бина
        •возможное количество создаваемых бинов

        singletone–дефолтный scope.
        •такой бин создаётся сразу после прочтения Spring Container-ом конфигфайла.
        •является общим для всех, кто запросит его у Spring Container-а.
        •подходит для stateless объектов.

        prototype
        •такой бин создаётся только после обращения к Spring Container-у с помощью метода getBean.
        •для каждого такого обращения создаётся новый бин в Spring Container-е.
        •подходит для statefulобъектов.
         */
        System.out.println("Singleton beans: ");


        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext2.xml");

        Dog myDog = context.getBean("petSingleton", Dog.class);
        myDog.setName("Belka");
        Dog yourDog = context.getBean("petSingleton", Dog.class);
        yourDog.setName("Strelka");

        System.out.println("Переменные ссылаются на один и тот же объект: " +
                (myDog == yourDog));
        System.out.println("Адрес объекта в памяти: " + myDog + "; имя объекта: " + myDog.getName());
        System.out.println("Адрес объекта в памяти: " + yourDog + "; имя объекта: " + yourDog.getName());
        System.out.println("---------------------------------------");

        System.out.println("Prototype beans: ");
        Dog myDog2 = context.getBean("petPrototype", Dog.class);
        myDog2.setName("Belka");
        Dog yourDog2 = context.getBean("petPrototype", Dog.class);
        yourDog2.setName("Strelka");

        System.out.println("Переменные ссылаются на один и тот же объект: " +
                (myDog2 == yourDog2));
        System.out.println("Адрес объекта в памяти: " + myDog2 + "; имя объекта: " + myDog2.getName());
        System.out.println("Адрес объекта в памяти: " + yourDog2 + "; имя объекта: " + yourDog2.getName());


        Cat prototypeCat = context.getBean("prototypeCat", Cat.class);
        prototypeCat.setName("prototypeBeanCat");
        prototypeCat.say();
        Cat singletonCat = context.getBean("singletonCat", Cat.class);
        singletonCat.setName("singletonBeanCat");
        singletonCat.say();

        context.close();

        /*
    Методы init и destroy:
        •У данных методов access modifier может быть любым
        •У данных методов return type может быть любым. Но из-за того, что возвращаемое значение мы никак не можем использовать, чаще всего return type –это void.
        •Называться данные методы могут как угодно.
        •В данных методах не должно быть параметров.

    Если у бина scope = prototype, то:
        •для этого бина destroy-method вызываться не будет
        •init-method будет вызываться для каждого новосозданного бина.
        •программисту необходимо самостоятельно писать код для закрытия/освобождения ресурсов, которые были использованы в бине
         */
    }

    public static void configurationXMLandAnnotations() {
        /*
        Конфигурация с помощью аннотаций более короткий и быстрый способ, чем конфигурация с помощью XML файла.
        Процесс состоит из 2-х этапов:
        1.сканирование классов и поиск аннотации @Component
        2.Создание (регистрация) бина в Spring Container-е
         */

        System.out.println("Конфигурация с помощью аннотаций и XML файла, в XML файле указываем только директорию, " +
                "которую нужно сканировать на наличие бинов с аннотацией @Component: \n");
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext3.xml");

        Person person = context.getBean("personBean", Person.class);
        person.callPet();
        System.out.println("Фамилия из myApp.properties: " + person.getSurname() + "\nИмя из myApp.properties: " + person.getAge());

        context.close();
    }

    public static void configurationWithJAVA() {
        /*
        При использовании конфигурации с помощью Java кода, Spring Container будет представлен классом AnnotationConfigApplicationContext
         */
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyConfig_2.class); //можно менять конфиги
        Person person = context.getBean("personBean", Person.class);
        person.callPet();
        System.out.println("Фамилия из myApp.properties: " + person.getSurname() + "\nИмя из myApp.properties: " + person.getAge());
    }
}


//WARNING: Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.UnsatisfiedDependencyException:
//        Error creating bean with name 'personAnnoBean' defined in file [C:\Users\Lexxx\IdeaProjects\SpringProjects\Tregulov\SpringTraining\target\classes\InversionOfControl\PersonAnno.class]:
//        Unsatisfied dependency expressed through constructor parameter 0; nested exception is org.springframework.beans.factory.NoUniqueBeanDefinitionException:
//        No qualifying bean of type 'InversionOfControl.Animals.Pet' available: expected single matching bean but found 2: catBean,dogBean
//        Exception in thread "main" org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'personAnnoBean'
//        defined in file [C:\Users\Lexxx\IdeaProjects\SpringProjects\Tregulov\SpringTraining\target\classes\InversionOfControl\PersonAnno.class]:
//        Unsatisfied dependency expressed through constructor parameter 0; nested exception is org.springframework.beans.factory.NoUniqueBeanDefinitionException:
//        No qualifying bean of type 'InversionOfControl.Animals.Pet' available: expected single matching bean but found 2: catBean,dogBean
//        at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray