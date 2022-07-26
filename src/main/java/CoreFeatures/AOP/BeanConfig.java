package CoreFeatures.AOP;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "CoreFeatures.AOP")
@EnableAspectJAutoProxy
public class BeanConfig {
}
