package SpringMVC.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) // @CheckEmail мы будем использовать для полей
@Retention(RetentionPolicy.RUNTIME) // информация о нашей аннотации @CheckEmail должна сохраняться вплоть до выполнения кода
@Constraint(validatedBy = CheckEmailValidator.class)
public @interface CheckEmail {
    public String value() default "xyz.com"; //дефолтные значения нужны только тогда, когда в скобках @CheckEmail ничего не указываем
    public String message() default "email must ends with xyz.com";

    public Class<?>[] groups() default {}; // позволяет программистам разбивать аннотации по группам
    public Class<? extends Payload> [] payload() default {}; // используется для переноса информации о метаданных клиента
}
