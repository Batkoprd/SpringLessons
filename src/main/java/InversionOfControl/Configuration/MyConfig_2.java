package InversionOfControl.Configuration;

import InversionOfControl.Animals.Cat;
import InversionOfControl.Person;
import InversionOfControl.Animals.Pet;
import org.springframework.context.annotation.*;


/*
•Данный способ не использует сканирование пакета и поиск бинов. Здесь бины описываются в конфиг классе.
•Данный способ не использует аннотацию @Autowired. Здесь зависимости прописываются вручную.
•Название метода –это bean id.
•Аннотация @Bean перехватывает все обращения к бинуи регулирует его создание.
 */
@Configuration
@PropertySource("classpath:myApp.properties") //Аннотация @PropertySource указывает на property файл откуда мы можем использовать значения для полей
public class MyConfig_2 {

    @Bean
    @Scope("singleton")
    public Pet catBean() {
        return new Cat();
    }

    @Bean
    public Person personBean() {
        return new Person(catBean());
    }

}
