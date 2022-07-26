package AspectOrientedProgramming.MyExample;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//@Component
@Aspect
public class ExampleAspect {

    @Around("execution(* AspectOrientedProgramming.MyExample.ExampleClass(..))")
    public void aroundAspect() {
        System.out.println("AroundAspect");
    }

}
