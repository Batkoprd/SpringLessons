package AspectOrientedProgramming.MyExample;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("AspectOrientedProgramming.MyExample")
@EnableAspectJAutoProxy //@EnableAspectJAutoProxy позволяет нам за кулисами использовать Spring AOP Proxy
public class ExampleConfig {
}

