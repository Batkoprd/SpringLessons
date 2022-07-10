package AspectOrientedProgramming.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class MyPointcut {
    @Pointcut("execution(* AspectOrientedProgramming.Classes.UniLibrary.add*(..))")
    public void allAddMethods(){}
}
