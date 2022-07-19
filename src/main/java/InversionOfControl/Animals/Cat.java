package InversionOfControl.Animals;

import org.springframework.stereotype.Component;

//@Component("catBean")
public class Cat implements Pet {

    private String name;

    public Cat() {
        System.out.println("Пустой конструктор класса " + this.getClass().getSimpleName() + " " + this.getName() + ": " + "Cat bean is created.");
    }

    @Override
    public void say() {
        System.out.println(this.getClass().getSimpleName() + " " + this.getName() + ": Meow-meow");
    }

    public void init() {
        System.out.println(this.getClass().getSimpleName() + " " + this.getName() + " init method. Срабатывает при создании бина, до setName().");
    }

    public void destroy() {
        System.out.println(this.getClass().getSimpleName() + " " + this.getName() + " destroy method.");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
