package InversionOfControl.Animals;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// Если к аннотации @Component не прописать bean id, то бину будет назначен дефолтный id.
//Дефолтный bean id получается из имени класса, заменяя его первую заглавную букву на прописную.
@Component("dogBean")
@Scope("singleton")
public class Dog implements Pet {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dog() {
        System.out.println("Пустой конструктор класса " + this.getClass().getSimpleName() + ": bean is created.");
    }

    @Override
    public void say() {
        System.out.println(this.getClass().getSimpleName() + ": Bow-wow");
    }


    @PostConstruct
    public void init() {
        System.out.println(this.getClass().getSimpleName() + ": @PostConstruct init method.");
    }

    @PreDestroy
    public void destroy() {
        System.out.println(this.getClass().getSimpleName() + ": @PreDestroy destroy method.");
    }

}
