/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validators;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.validator.ValidatorException;

/**
 * Base class for validators.
 * @author : kasun eranda - 12216898
 */
public class BaseValidator {
    
    private ErrorMessage error;
    protected static final String FIELD_NAME_ATTR = "fieldName";
    private String fieldName;
    
    /**
     * Generates a ValidatorException with the appropriate error message.
     * @return The generated ValidatorException.
     * @throws ValidatorException If validation fails.
     */
    protected ValidatorException generateError() {
        String message = error != null ? error.getMessage(fieldName) : error.INVALID.getMessage(null);
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
        throw new ValidatorException(facesMessage);
    }
    
    /**
     * Sets the error message for validation.
     * @param error The error message to set.
     */
    protected void setError(ErrorMessage error) {
        this.error = error;
    }

    /**
     * Sets the name of the field being validated.
     * @param fieldName The name of the field.
     */
    protected void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
