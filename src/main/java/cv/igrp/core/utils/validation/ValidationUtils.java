package cv.igrp.core.utils.validation;

import java.util.regex.Pattern;

public class ValidationUtils {

    /**
     * Checks if a string is a valid email address.
     *
     * @param email the email address to check
     * @return true if the email is valid, false otherwise
     */
    public static boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email != null && email.matches(regex);
    }

    /**
     * Checks if a string is a valid phone number (simple validation).
     *
     * @param phoneNumber the phone number to check
     * @return true if the phone number is valid, false otherwise
     */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "^\\+?[0-9]*$";
        return phoneNumber != null && phoneNumber.matches(regex);
    }

    /**
     * Checks if a string is a valid numeric value.
     *
     * @param value the string to check
     * @return true if the string is numeric, false otherwise
     */
    public static boolean isNumeric(String value) {
        return value != null && value.matches("-?\\d+(\\.\\d+)?");
    }

    /**
     * Checks if a string is a valid URL.
     *
     * @param url the string to check
     * @return true if the string is a valid URL, false otherwise
     */
    public static boolean isValidURL(String url) {
        String regex = "^(https?|ftp)://[^ /$.?#].[^ ]*$";
        return url != null && url.matches(regex);
    }

}