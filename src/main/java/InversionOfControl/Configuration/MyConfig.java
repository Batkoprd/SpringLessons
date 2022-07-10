package InversionOfControl.Configuration;

import InversionOfControl.Cat;
import InversionOfControl.Person;
import InversionOfControl.Pet;
import org.springframework.context.annotation.*;

@Configuration
@PropertySource("classpath:myApp.properties")
//@ComponentScan("InversionOfControl")
public class MyConfig {

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
