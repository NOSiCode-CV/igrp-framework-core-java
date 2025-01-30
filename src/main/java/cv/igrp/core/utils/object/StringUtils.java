package cv.igrp.core.utils.object;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringUtils {

    /**
     * Normalizes the input text by removing accents and converting it to lowercase.
     *
     * @param input the input string to normalize
     * @return a normalized version of the string (lowercase and without accents)
     */
    public static String normalizeText(String input) {
        if (input == null) {
            return null;
        }
        // Remove accents and convert to lowercase
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized).replaceAll("").toLowerCase();
    }

    /**
     * Normalizes the name by applying text normalization (accents removal and lowercase),
     * and removes any special characters, keeping only alphanumeric characters and spaces.
     *
     * @param string the string to normalize
     * @return a normalized version of the string, suitable for names (alphanumeric only)
     */
    public static String normalizeName(String string) {
        return normalizeText(string).replaceAll("[^-a-zA-Z0-9\\s]", "");
    }

    /**
     * Trims whitespace from both ends of a string and replaces multiple spaces with a single space.
     *
     * @param input the input string to clean
     * @return the cleaned string
     */
    public static String cleanWhitespace(String input) {
        if (input == null) {
            return null;
        }
        return input.trim().replaceAll("\\s+", " ");
    }

    /**
     * Checks if a string is null, empty, or contains only whitespace.
     *
     * @param input the string to check
     * @return true if the string is null, empty, or only whitespace; false otherwise
     */
    public static boolean isNullOrBlank(String input) {
        return input == null || input.trim().isEmpty();
    }

    /**
     * Replaces all occurrences of a given substring with another substring in the provided string.
     *
     * @param input        the input string
     * @param target       the substring to replace
     * @param replacement  the substring to replace with
     * @return the modified string
     */
    public static String replaceAll(String input, String target, String replacement) {
        if (input == null || target == null || replacement == null) {
            return input;
        }
        return input.replaceAll(Pattern.quote(target), replacement);
    }

    /**
     * Converts a string to camel case (e.g., "hello world" becomes "helloWorld").
     *
     * @param input the input string to convert
     * @return the camel case version of the string
     */
    public static String toCamelCase(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        String[] words = input.split("\\s+");
        StringBuilder camelCaseString = new StringBuilder(words[0].toLowerCase());
        for (int i = 1; i < words.length; i++) {
            camelCaseString.append(words[i].substring(0, 1).toUpperCase())
                    .append(words[i].substring(1).toLowerCase());
        }
        return camelCaseString.toString();
    }

    /**
     * Converts a string to title case (e.g., "hello world" becomes "Hello World").
     *
     * @param input the input string to convert
     * @return the title case version of the string
     */
    public static String toTitleCase(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        String[] words = input.split("\\s+");
        StringBuilder titleCaseString = new StringBuilder();
        for (String word : words) {
            titleCaseString.append(word.substring(0, 1).toUpperCase())
                    .append(word.substring(1).toLowerCase())
                    .append(" ");
        }
        return titleCaseString.toString().trim();
    }

    /**
     * Checks if a string contains only digits.
     *
     * @param input the input string to check
     * @return true if the string contains only digits, false otherwise
     */
    public static boolean isNumeric(String input) {
        if (input == null) {
            return false;
        }
        return input.matches("\\d+");
    }

    /**
     * Converts a string to snake_case (e.g., "Hello World" becomes "hello_world").
     *
     * @param input the input string to convert
     * @return the snake_case version of the string
     */
    public static String toSnakeCase(String input) {
        if (input == null) {
            return null;
        }
        return input.replaceAll("([a-z])([A-Z]+)", "$1_$2")
                .toLowerCase().replaceAll("\\s+", "_");
    }

    /**
     * Checks if a string contains a specific substring (case insensitive).
     *
     * @param input    the string to search within
     * @param keyword  the substring to search for
     * @return true if the input contains the keyword, false otherwise
     */
    public static boolean containsIgnoreCase(String input, String keyword) {
        if (input == null || keyword == null) {
            return false;
        }
        return input.toLowerCase().contains(keyword.toLowerCase());
    }

    /**
     * Reverses the characters of a string.
     *
     * @param input the input string to reverse
     * @return the reversed string
     */
    public static String reverse(String input) {
        if (input == null) {
            return null;
        }
        return new StringBuilder(input).reverse().toString();
    }

    /**
     * Converts a string to a valid email format (basic check for email structure).
     *
     * @param input the string to check and convert
     * @return true if the string is a valid email format, false otherwise
     */
    public static boolean isValidEmail(String input) {
        if (input == null) {
            return false;
        }
        return input.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }
}
