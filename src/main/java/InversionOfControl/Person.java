package InversionOfControl;

import InversionOfControl.Animals.Pet;
import org.springframework.beans.factory.annotation.Value;

public class Person {
    private Pet pet;
    @Value("${person.surname}")
    private String surname;

    @Value("${person.age}")
    private int age;
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        System.out.println("Class person: set surname.");
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        System.out.println("Class person: set age.");
        this.age = age;
    }

    public Person() {
        System.out.println("Пустой конструктор класса " + this.getClass().getSimpleName() + ": Person bean is created.");
    }

    public Person(Pet pet) {
        System.out.println("Конструктор Person(Pet pet) класса "+ this.getClass().getSimpleName() + ": Person bean is created.");
        this.pet = pet;
    }

    public void setPet(Pet pet) {
        System.out.println("Class person: set pet.");
        this.pet = pet;
    }

    public void callPet() {
        System.out.println(this.getClass().getSimpleName() + ": Hello, my lovely Pet!");
        pet.say();
    }
}
