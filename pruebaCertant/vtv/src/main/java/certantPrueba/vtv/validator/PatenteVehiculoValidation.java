package certantPrueba.vtv.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PatenteVehiculoValidation implements ConstraintValidator<PatenteVehiculo, String> {

    @Override
    public void initialize(PatenteVehiculo patenteVehiculo) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            if (validaMotoPatente(value) || validaAutoCamionetaPatente(value)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }

    }

    private boolean validaMotoPatente(String value) {

        Pattern patentePattern = Pattern.compile("^[A-Z]{1}[0-9]{2} [0-9]{1}[A-Z]{3}$");
        Matcher patenteMattcher = patentePattern.matcher(value);
        if (patenteMattcher.matches()) {
            return true;
        }
        return false;
    }

    private boolean validaAutoCamionetaPatente(String value) {

        Pattern patentePattern = Pattern.compile("^[A-Z]{3} [0-9]{2} [A-Z]{2}$");
        Matcher patenteMattcher = patentePattern.matcher(value);
        if (patenteMattcher.matches()) {
            return true;
        }
        return false;
    }
}
