package InversionOfControl.Configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/*
Аннотация @Configuration означает, что данный класс является конфигурацией. С помощью аннотации @ComponentScan мы показываем,
какой пакет нужно сканировать на наличие бинов и разных аннотаций.
 */
@Configuration
@ComponentScan("InversionOfControl")
public class MyConfig_1 {
}
