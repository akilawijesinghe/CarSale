package Validators;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validator for checking valid phone number.
 *
 * @author : kasun eranda - 12216898
 */
@FacesValidator(value = "isPhoneNumber")
public class IsPhoneNumber extends BaseValidator implements Validator {

    private Pattern pattern;
    private Matcher matcher;

    /**
     * Regex patter for the phone number
     */
    private static final String REGEX_PATTERN = "^(?:\\+[0-9]{1,3}[-.\\s]?)?\\(?[0-9]{3}\\)?[-.\\s]?[0-9]{3}[-.\\s]?[0-9]{4}$";

    /**
     * Validates if a field not contain valid phone number.
     *
     * @param context The FacesContext for the current request.
     * @param component The UIComponent being validated.
     * @param value The value to validate.
     * @throws ValidatorException If validation fails.
     */
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        setError(ErrorMessage.INVALID_PHONE);
        setFieldName((String) component.getAttributes().get(FIELD_NAME_ATTR));

        String componentValue = value.toString();

        pattern = Pattern.compile(REGEX_PATTERN);
        matcher = pattern.matcher(componentValue);

        if (!matcher.matches()) {
            throw generateError();
        }
    }
}
