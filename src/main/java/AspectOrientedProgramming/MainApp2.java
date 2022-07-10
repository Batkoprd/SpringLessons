package AspectOrientedProgramming;

import AspectOrientedProgramming.Classes.Student;
import AspectOrientedProgramming.Classes.University;
import AspectOrientedProgramming.Configuration.MyConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp2 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        University university = context.getBean("university", University.class);
        university.addStudent();
        try {

            List<Student> students = university.getStudents();
            System.out.println(students);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Было поймано исключение " + e);
        }

        context.close();
    }
}
