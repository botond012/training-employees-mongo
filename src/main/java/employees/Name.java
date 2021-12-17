package employees;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NameValidator.class)
public @interface Name {
	String message() default "Invalid Name";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	int maxLength() default 50;
}

