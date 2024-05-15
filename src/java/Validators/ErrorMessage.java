/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Validators;

/**
 * Represents error messages for validation.
 * @author : kasun eranda - 12216898
 */
public enum ErrorMessage {
    /**
     * Error message for an invalid value.
     */
    INVALID("Please enter a value."),

    /**
     * Error message for an empty field.
     * It replaces {0} with the field name.
     */
    EMPTY_FIELD("Please enter a value for {0}. Field cannot be empty."),

    /**
     * Error message for an invalid regex format.
     * It replaces {0} with the field name.
     */
    INVALID_FORMAT("Please enter valid {0}. Invalid format."),

    /**
     * Error message for an invalid email format.
     * It replaces {0} with the field name.
     */
    INVALID_EMAIL("Please enter valid {0}");

    private final String message;

    /**
     * Constructs an ErrorMessage enum with a message.
     * @param message The error message.
     */
    ErrorMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the formatted error message with the field name.
     * @param fieldName The name of the field.
     * @return The formatted error message.
     */
    public String getMessage(String fieldName) {
        return message.replace("{0}", fieldName);
    }
}
