package cv.igrp.framework.core.utils.math;

import java.math.BigDecimal;
import java.math.BigInteger;

import static cv.igrp.framework.core.utils.object.StringUtils.isNumeric;

public class MathUtils {

    /**
     * Checks if the value is a valid Double.
     *
     * @param value the value to check
     * @return true if the value is a valid Double, false otherwise
     */
    public static boolean isDouble(Object value) {
        return value instanceof Double || (value instanceof String && isNumeric((String) value));
    }

    /**
     * Checks if the value is a valid Float.
     *
     * @param value the value to check
     * @return true if the value is a valid Float, false otherwise
     */
    public static boolean isFloat(Object value) {
        return value instanceof Float || (value instanceof String && isNumeric((String) value));
    }

    /**
     * Converts a string to BigDecimal.
     *
     * @param value the string to convert
     * @return the BigDecimal representation of the string
     */
    public static BigDecimal toBigDecimal(String value) {
        return new BigDecimal(value);
    }

    /**
     * Converts a string to BigInteger.
     *
     * @param value the string to convert
     * @return the BigInteger representation of the string
     */
    public static BigInteger toBigInteger(String value) {
        return new BigInteger(value);
    }

    /**
     * Converts a string to BigInteger with a default value.
     *
     * @param value        the string to convert
     * @param defaultValue the default value if the string is invalid
     * @return the BigInteger representation of the string or the default value
     */
    public static BigInteger toBigInteger(String value, BigInteger defaultValue) {
        try {
            return new BigInteger(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Converts a string to Double.
     *
     * @param value the string to convert
     * @return the Double representation of the string
     */
    public static Double toDouble(String value) {
        return Double.parseDouble(value);
    }

    /**
     * Converts a string to Double with a default value.
     *
     * @param value        the string to convert
     * @param defaultValue the default value if the string is invalid
     * @return the Double representation of the string or the default value
     */
    public static Double toDouble(String value, double defaultValue) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Converts a string to Float.
     *
     * @param value the string to convert
     * @return the Float representation of the string
     */
    public static Float toFloat(String value) {
        return Float.parseFloat(value);
    }

    /**
     * Converts a string to Integer.
     *
     * @param value the string to convert
     * @return the Integer representation of the string
     */
    public static Integer toInt(String value) {
        return Integer.parseInt(value);
    }

    /**
     * Converts a string to Integer with a default value.
     *
     * @param value        the string to convert
     * @param defaultValue the default value if the string is invalid
     * @return the Integer representation of the string or the default value
     */
    public static Integer toInt(String value, int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Converts a string to Long.
     *
     * @param value the string to convert
     * @return the Long representation of the string
     */
    public static Long toLong(String value) {
        return Long.parseLong(value);
    }

    /**
     * Converts a string to Long with a default value.
     *
     * @param value        the string to convert
     * @param defaultValue the default value if the string is invalid
     * @return the Long representation of the string or the default value
     */
    public static Long toLong(String value, long defaultValue) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Converts a string to Short.
     *
     * @param value the string to convert
     * @return the Short representation of the string
     */
    public static Short toShort(String value) {
        return Short.parseShort(value);
    }

    /**
     * Converts a string to Short with a default value.
     *
     * @param value        the string to convert
     * @param defaultValue the default value if the string is invalid
     * @return the Short representation of the string or the default value
     */
    public static Short toShort(String value, short defaultValue) {
        try {
            return Short.parseShort(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Converts an array of integers to an array of strings.
     *
     * @param array the array of integers
     * @return the array of strings
     */
    public static String[] convertArrayIntToArrayString(int[] array) {
        String[] result = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = String.valueOf(array[i]);
        }
        return result;
    }

    /**
     * Converts an array of floats to an array of strings.
     *
     * @param array the array of floats
     * @return the array of strings
     */
    public static String[] convertArrayFloatToArrayString(float[] array) {
        String[] result = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = String.valueOf(array[i]);
        }
        return result;
    }

    /**
     * Converts an array of doubles to an array of strings.
     *
     * @param array the array of doubles
     * @return the array of strings
     */
    public static String[] convertArrayDoubleToArrayString(double[] array) {
        String[] result = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = String.valueOf(array[i]);
        }
        return result;
    }

    /**
     * Converts an array of shorts to an array of strings.
     *
     * @param array the array of shorts
     * @return the array of strings
     */
    public static String[] convertArrayShortToArrayString(short[] array) {
        String[] result = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = String.valueOf(array[i]);
        }
        return result;
    }

    /**
     * Converts an array of objects to an array of integers.
     *
     * @param array the array of objects
     * @param <N>   the type of the array elements
     * @return the array of integers
     */
    public static <N> int[] convertArrayObjectToArrayInt(N[] array) {
        int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = Integer.parseInt(array[i].toString());
        }
        return result;
    }

    /**
     * Converts an array of objects to an array of shorts.
     *
     * @param array the array of objects
     * @param <N>   the type of the array elements
     * @return the array of shorts
     */
    public static <N> short[] convertArrayObjectToArrayShort(N[] array) {
        short[] result = new short[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = Short.parseShort(array[i].toString());
        }
        return result;
    }

    /**
     * Converts an array of objects to an array of floats.
     *
     * @param array the array of objects
     * @param <N>   the type of the array elements
     * @return the array of floats
     */
    public static <N> float[] convertArrayObjectToArrayFloat(N[] array) {
        float[] result = new float[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = Float.parseFloat(array[i].toString());
        }
        return result;
    }

    /**
     * Converts an array of objects to an array of doubles.
     *
     * @param array the array of objects
     * @param <N>   the type of the array elements
     * @return the array of doubles
     */
    public static <N> double[] convertArrayObjectToArrayDouble(N[] array) {
        double[] result = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = Double.parseDouble(array[i].toString());
        }
        return result;
    }

    /**
     * Converts an array of objects to an array of Integers.
     *
     * @param array the array of objects
     * @param <N>   the type of the array elements
     * @return the array of Integers
     */
    public static <N> Integer[] convertArrayObjectToArrayInteger(N[] array) {
        Integer[] result = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = Integer.parseInt(array[i].toString());
        }
        return result;
    }

    /**
     * Returns the number of digits in a number.
     *
     * @param number the number to check
     * @return the number of digits
     */
    public static int digits(Number number) {
        return number.toString().replaceAll("[^0-9]", "").length();
    }

    /**
     * Returns the number of digits in a string representing a number.
     *
     * @param number the string to check
     * @return the number of digits
     */
    public static int digits(String number) {
        return number.replaceAll("[^0-9]", "").length();
    }

    /**
     * Checks if the value is an integer.
     *
     * @param value the value to check
     * @return true if the value is an integer, false otherwise
     */
    public static boolean isInt(Object value) {
        return value instanceof Integer || (value instanceof String && value.toString().matches("-?\\d+"));
    }
}

