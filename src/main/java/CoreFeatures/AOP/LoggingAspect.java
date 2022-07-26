package CoreFeatures.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Pointcut("execution(* CoreFeatures.AOP.ShoppingCart.quantity(..))")
    public void afterReturningPointCut(){}

    //what we want to call and when we want it to be called
    @Before("execution(* CoreFeatures.AOP.ShoppingCart.checkout(..))")
    public void beforeLogger(JoinPoint jp) {
        System.out.println("checkout signature: " + jp.getSignature() + "; Fist argument of the checkout method: " + jp.getArgs()[0].toString());
        System.out.println("Before ShoppingCart checkout logging aspect.");
    }

    @After("execution(* CoreFeatures.AOP.ShoppingCart.checkout(..))")
    public void afterLogger() {
        System.out.println("After ShoppingCart checkout logging aspect.");
    }

    @AfterReturning(pointcut = "afterReturningPointCut()", returning = "returnValue")
    public void afterReturning(int returnValue) {
        System.out.println("After Returning logging aspect: " + returnValue);
    }

}
