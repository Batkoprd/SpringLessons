package InversionOfControl;

import InversionOfControl.Animals.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("personAnnoBean")
public class PersonAnno {

    /*
Если при использовании @Autowired подходящих по типу бинов больше одного, то выбрасывается исключение. Предотвратить
выброс данного исключения можно конкретно указав, какой бин должен быть внедрён. Для этого и используют аннотацию @Qualifier.
     */
//    @Autowired
//    @Qualifier("dogBean")
    private Pet pet;
    @Value("${person.surname}")
    private String surname;

    @Value("${person.age}")
    private int age;
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        System.out.println("Class " + this.getClass().getSimpleName() +  ": set surname.");
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        System.out.println("Class " + this.getClass().getSimpleName() + ": set age.");
        this.age = age;
    }


    public PersonAnno() {
        System.out.println("Пустой конструктор класса " + this.getClass().getSimpleName() + ": Person bean is created.");
    }

    @Autowired
    public PersonAnno(@Qualifier("catBean") Pet pet) {
        System.out.println("Конструктор PersonAnno(@Qualifier(\"catBean\") Pet pet) c @Autowired класса "+ this.getClass().getSimpleName() + ": PersonAnno bean is created.");
        this.pet = pet;
    }

    @Autowired
    public void setPet(@Qualifier("dogBean") Pet pet) {
        System.out.println("@Autowired для сеттера: set pet @dogBean.");
        this.pet = pet;
    }

    public void callPet() {
        System.out.println(this.getClass().getSimpleName() + ": Hello, my lovely Pet!");
        pet.say();
    }
}
