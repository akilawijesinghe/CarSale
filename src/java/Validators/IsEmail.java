package Validators;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validator for checking email format.
 * @author : kasun eranda - 12216898
 */
@FacesValidator(value = "isEmail")
public class IsEmail extends BaseValidator implements Validator {

    private Pattern pattern;
    private Matcher matcher;

    /**
     * Regex patter for the email
     */
    private static final String EMAIL_REGEX_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    /**
     * Validates the email format.
     * @param context The FacesContext for the current request.
     * @param component The UIComponent being validated.
     * @param value The value to validate.
     * @throws ValidatorException If validation fails.
     */
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        setError(ErrorMessage.INVALID_EMAIL);
        setFieldName((String) component.getAttributes().get(FIELD_NAME_ATTR));

        String componentValue = value.toString();

        pattern = Pattern.compile(EMAIL_REGEX_PATTERN);
        matcher = pattern.matcher(componentValue);

        if (!matcher.matches()) {
            throw generateError();
        }
    }
}