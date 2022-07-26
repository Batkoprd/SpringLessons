package AspectOrientedProgramming.Configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("AspectOrientedProgramming")
@EnableAspectJAutoProxy //@EnableAspectJAutoProxy позволяет нам за кулисами использовать Spring AOP Proxy
public class MyConfig {
}
