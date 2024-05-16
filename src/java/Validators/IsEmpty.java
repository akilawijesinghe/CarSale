package Validators;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

/**
 * Validator for checking if a field is empty.
 * @author : kasun eranda - 12216898 
 */
@FacesValidator(value = "isEmpty")
public class IsEmpty extends BaseValidator implements Validator {

    /**
     * Validates if a field is empty.
     * @param context The FacesContext for the current request.
     * @param component The UIComponent being validated.
     * @param value The value to validate.
     * @throws ValidatorException If validation fails.
     */
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        setError(ErrorMessage.EMPTY_FIELD);
        setFieldName((String) component.getAttributes().get(FIELD_NAME_ATTR));
        
        if (value == null || ((String) value).trim().isEmpty()) {
            throw generateError();
        }
    }
}
