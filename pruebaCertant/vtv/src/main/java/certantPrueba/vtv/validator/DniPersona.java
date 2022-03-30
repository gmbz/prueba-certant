package certantPrueba.vtv.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = DniPersonaValidation.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DniPersona {

    public String message() default "DNI invalido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
