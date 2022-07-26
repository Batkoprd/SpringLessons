package AspectOrientedProgramming.MyExample;

import AspectOrientedProgramming.Configuration.MyConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ExampleMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ExampleConfig.class);
        ExampleClass obj = context.getBean("exampleClass", ExampleClass.class);
        obj.regularMethod();

        context.close();

    }
}
//WARNING: Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.BeanCreationException:
//        Error creating bean with name 'myConfig': Initialization of bean failed; nested exception is java.lang.IllegalArgumentException:
//        warning no match for this type name: AspectOrientedProgramming.MyExample [Xlint:invalidAbsoluteTypeName]
//        Exception in thread "main" org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'myConfig': Initialization of bean failed;
//        nested exception is java.lang.IllegalArgumentException: warning no match for this type name: AspectOrientedProgramming.MyExample [Xlint:invalidAbsoluteTypeName]
