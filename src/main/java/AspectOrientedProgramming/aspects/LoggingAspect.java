package AspectOrientedProgramming.aspects;

import AspectOrientedProgramming.Classes.Book;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect //@Aspect говорит о том, что это не простой класс, а Aspect. Поэтому к данному классу Spring будет относиться по другому.
@Order(10)
public class LoggingAspect {

//    @Pointcut("execution(* AspectOrientedProgramming.Classes.UniLibrary.*(..))")
//    private void allMethodsFromUniLibrary(){}
//
//    @Pointcut("execution(public void AspectOrientedProgramming.Classes.UniLibrary.returnMagazine())")
//    private void returnMagazineUnilibrary(){}
//
//    @Pointcut("allMethodsFromUniLibrary() && !returnMagazineUnilibrary()")
//    private void allMethodsExceptReturnMagazineFromUniLibrary(){}
//
//    @Before("allMethodsExceptReturnMagazineFromUniLibrary()")
//    public void beforeAllMethodsExceptReturnMagazineFromUniLibraryAdvice() {
//        System.out.println("beforeAllMethodsExceptReturnMagazineFromUniLibraryAdvice: Log #10");
//    }
//
//    @Pointcut("execution(* AspectOrientedProgramming.Classes.UniLibrary.get*())")
//    private void allGetMethodsUniLibrary(){}
//    @Pointcut("execution(* AspectOrientedProgramming.Classes.UniLibrary.return*())")
//    private void allReturnMethodsUniLibrary(){}
//
//    @Pointcut("allGetMethodsUniLibrary() || allReturnMethodsUniLibrary()")
//    private void allGetAndReturnMethodsUniLibrary(){}
//
//    @Before("allGetMethodsUniLibrary()")
//    public void beforeGetLoggingAdvice(){
//        System.out.println("beforeGetLoggingAdvice: writing Log #1");
//    }
//
//    @Before("allReturnMethodsUniLibrary()")
//    public void beforeReturnLoggingAdvice(){
//        System.out.println("beforeReturnLoggingAdvice: writing Log #2");
//    }
//
//    @Before("allGetAndReturnMethodsUniLibrary()")
//    public void beforeGetAndReturnLoggingAdvice(){
//        System.out.println("beforeGetAndReturnLoggingAdvice: writing Log #3");
//    }

    //Before –выполняется до метода с основной логикой
    @Before("AspectOrientedProgramming.aspects.MyPointcut.allAddMethods()") //поинткат, когда должен выполниться сквозной код
    public void beforeAddLoggingAdvice(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("methodSignature = " + methodSignature);
        System.out.println("methodSignature.getMethod() = " + methodSignature.getMethod());
        System.out.println("methodSignature.getReturnType() = " + methodSignature.getReturnType());
        System.out.println("methodSignature.getName() = " + methodSignature.getName());

        if (methodSignature.getName().equals("addBook")) {
            Object[] arguments = joinPoint.getArgs();
            for (Object o : arguments) {
                if (o instanceof Book) {
                    Book myBook = (Book) o;
                    System.out.println("Информация о книге, название/автор/год издания: " + myBook.getName() + " " + myBook.getAuthor() + " " + myBook.getYearOfPublication());
                }
                else if (o instanceof String) {
                    System.out.println("Книгу в библиотеку добавил " + o);
                }
            }
        }

        System.out.println("beforeAddLoggingAdvice: логирование попытки получить книгу или журнал");
        System.out.println("------------------------------------");
    }

//    @Before("execution(* returnBook())") //поинткат, когда должен выполниться сквозной код
//    public void beforeReturnBookAdvice() {
//        System.out.println("beforeReturnBookAdvice: попытка вернуть книгу");
//    }
}
