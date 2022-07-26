package AspectOrientedProgramming.aspects;

import AspectOrientedProgramming.Classes.Student;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Aspect
public class UniversityLoggingAspect {
    @Before("execution(* getStudents())")
    public void beforeGetStudentsLoggingAdvice() {
        System.out.println("beforeGetStudentsLoggingAdvice: логгируем получение списка студентов перед методом getStudents");
    }

    @AfterReturning(pointcut = "execution(* getStudents())",
    returning = "students")
    public void afterReturningGetStudentsLoggingAdvice(List<Student> students) {
        Student firstStudent = students.get(0);
        String nameSurname = firstStudent.getNameSurname();
        nameSurname = "Mr. " + nameSurname;
        firstStudent.setNameSurname(nameSurname);

        System.out.println("afterReturningGetStudentsLoggingAdvice: логгируем получение списка студентов после  метода getStudents");
    }


    @AfterThrowing(pointcut = "execution(* getStudents())"
                , throwing = "exception") //Не влияет на протекание программы при выбрасывании исключения. С помощью @AfterThrowingAdvice можно получить доступ к исключению, которое выбросилось из метода с основной логикой.
    public void afterThrowingGetStudentsLoggingAdvice(Throwable exception) {
        System.out.println("afterThrowingGetStudentsLoggingAdvice: логгируем выброс исключения " + exception);
    }

    @After("execution(* getStudents())")
    public void afterGetStudentsLoggingAdvice() {
        System.out.println("afterGetStudentsLoggingAdvice: логгируем нормальное окончание работы метода или выброс исключения");

    }
}
