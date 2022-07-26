package CoreFeatures.AOP;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AuthenticationAspect {

    @Pointcut("within(CoreFeatures.AOP..*)") //within defines which type of class we want to execute on all the methods available
    public void authenticationPointCut(){

    }

    @Pointcut("within(CoreFeatures.AOP.ShoppingCart.*)")
    public void authorizationPointCut(){

    }

    @Before("authenticationPointCut() || authorizationPointCut()")
    public void authenticate() {
        System.out.println("Authenticating the Request");
    }

}
