package Validators;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validator for checking number format.
 * @author : kasun eranda - 12216898
 */
@FacesValidator(value = "isNumber")
public class IsNumber extends BaseValidator implements Validator {

    private Pattern pattern;
    private Matcher matcher;

    /**
     * Regex patter for the email
     */
    private static final String DIGIT_REGEX_PATTERN = "^-?[1-9]\\d*$";

    /**
     * Validates the number format.
     * @param context The FacesContext for the current request.
     * @param component The UIComponent being validated.
     * @param value The value to validate.
     * @throws ValidatorException If validation fails.
     */
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        setError(ErrorMessage.INVALID_NUMBER);
        setFieldName((String) component.getAttributes().get(FIELD_NAME_ATTR));

        String componentValue = value.toString();

        pattern = Pattern.compile(DIGIT_REGEX_PATTERN);
        matcher = pattern.matcher(componentValue);

        if (!matcher.matches()) {
            throw generateError();
        }

    }
}