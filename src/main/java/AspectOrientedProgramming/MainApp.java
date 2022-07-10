package AspectOrientedProgramming;

import AspectOrientedProgramming.Classes.Book;
import AspectOrientedProgramming.Classes.SchoolLibrary;
import AspectOrientedProgramming.Classes.UniLibrary;
import AspectOrientedProgramming.Configuration.MyConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
//        schoolLibrary.getBook();

        context.close();
    }
}
