package AspectOrientedProgramming;

import AspectOrientedProgramming.Classes.Book;
import AspectOrientedProgramming.Classes.SchoolLibrary;
import AspectOrientedProgramming.Classes.UniLibrary;
import AspectOrientedProgramming.Configuration.MyConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
AOP –парадигма программирования, основанная на идее разделения основного и служебного функционала.
Служебный функционал записывается в Aspect-классы. В основе Aspect заключена сквозная логика(cross-cutting logic).
К сквозному функционалу относят:
•Логирование
•Проверка прав (security check)
•Обработка транзакций
•Обработка исключений
•Кэширование
•И т.д.
 */

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyConfig.class);

        UniLibrary uniLibrary = context.getBean("uniLibrary", UniLibrary.class);
        Book book = context.getBean("book", Book.class);

        uniLibrary.getBook();
        uniLibrary.addBook("John", book);
        uniLibrary.addMagazine();

        SchoolLibrary schoolLibrary = context.getBean("schoolLibrary", SchoolLibrary.class);
        schoolLibrary.getBook();

        context.close();
    }
}
/*
Плюсы AOP:
•Сквозной функционал сосредоточен в 1-м или нескольких обособленных классах. Это позволяет легче его изменять.
•Становится легче добавлять новые сквозные работы для нашего основного кода или имеющиеся сквозные работы для новых классов. Это достигается благодаря конфигурации аспектов.
•Бизнес-код приложения избавляется от сквозного кода, становится меньше и чище. Работать с ним становится легче.
Минус AOP:
•Дополнительное время на работу аспектов

AOP frameworks:
Spring AOP - Предоставляет самую распространённую и необходимую функциональность AOP, Простой в использовании;
AspectJ - Предоставляет всю функциональность AOP, Более сложный в использовании;

Aspect –это класс, отвечающий за сквозную функциональность.
 */