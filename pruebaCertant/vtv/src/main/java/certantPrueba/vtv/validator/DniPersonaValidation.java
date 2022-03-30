package certantPrueba.vtv.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DniPersonaValidation implements ConstraintValidator<DniPersona, String> {

    @Override
    public void initialize(DniPersona dniPersona) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            Pattern patentePattern = Pattern.compile("^[0-9]{8}$");
            Matcher patenteMattcher = patentePattern.matcher(value);
            if (patenteMattcher.matches()) {
                return true;
            }
            return false;

        } catch (Exception e) {
            return false;
        }

    }

}
