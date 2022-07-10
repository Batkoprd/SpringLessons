package AspectOrientedProgramming.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AroundTypeAdviceLoggingAspect {

    /*
    С помощью @Around Advice возможно:
        1)произвести какие-либо действия до работы target метода;
        2)произвести какие-либо действия после работы target метода;
        3)получить результат работы target метода/изменить его;
        4)предпринять какие-либо действия, если из target метода выбрасывается исключение.
     */

//    @Around("execution(public String returnBook())")
//    public Object aroundReturnBookLoggingAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        System.out.println("aroundReturnBookLoggingAdvice: в библиотеку пытаются книгу");
//
//        long begin = System.currentTimeMillis();
//        Object targetMethodResult = proceedingJoinPoint.proceed();
//        long end = System.currentTimeMillis();
//
//
//        System.out.println("aroundReturnBookLoggingAdvice: в библиотеку вернули книгу");
//        System.out.println("aroundReturnBookLoggingAdvice: метод returnBook выполнил работу за " + (end - begin));
//        return targetMethodResult;
//    }



    /*
    Используя @Around Advice возможно предпринять следующие действия, если из target метода выбрасывается исключение:
    •Ничего не делать
    •Обрабатывать исключение
    •Пробрасывать исключение дальше
     */

    @Around("execution(public String returnBook())") //Обработка исключений в Around Advice
    public Object aroundReturnBookLoggingAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("aroundReturnBookLoggingAdvice: в библиотеку пытаются книгу");
        Object targetMethodResult = null;
        try {
            targetMethodResult = proceedingJoinPoint.proceed();

        } catch (ArithmeticException e) {
            System.out.println("aroundReturnBookLoggingAdvice: было поймано исключение  " + e);
            targetMethodResult = "Неизвестное название книги";
            throw e;
        }
        System.out.println("aroundReturnBookLoggingAdvice: в библиотеку вернули книгу");
        return targetMethodResult;
    }

}
