package InversionOfControl.Configuration;

import InversionOfControl.Animals.Cat;
import InversionOfControl.Person;
import InversionOfControl.Animals.Pet;
import org.springframework.context.annotation.*;


/*
•Данный способ не использует сканирование пакета и поиск бинов. Здесь бины описываются в конфиг классе.
•Данный способ не использует аннотацию @Autowired. Здесь зависимости прописываются вручную.
•Название метода –это bean id.
•Аннотация @Bean перехватывает все обращения к бину и регулирует его создание.
 */
@Configuration
@PropertySource("classpath:myApp.properties") //Аннотация @PropertySource указывает на property файл откуда мы можем использовать значения для полей
public class MyConfig_2 {

    @Bean
    @Scope("singleton") //если скоуп синглтон, то до return cat может не дойти, если мы вызовем бин Person(catBean()) туда поместится готовый бин Cat
    public Pet catBean() {
        return new Cat();
    }

    @Bean //При запуске приложения, тк бин синглтон, то он сразу будет создан и помещен в спринг контейнер
    public Person personBean() {
//        Cat cat = (Cat) catBean();
//        cat.setName("Кошка человека");
        return new Person(catBean());
    }

}
