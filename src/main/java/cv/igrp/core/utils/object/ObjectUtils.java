package cv.igrp.core.utils.object;

import java.util.Collection;
import java.util.Map;

public class ObjectUtils {

    /**
     * Checks if the given object is not null.
     *
     * @param value the object to check
     * @return true if the object is not null, false otherwise
     */
    public static boolean isNotNull(Object value) {
        return value != null;
    }

    /**
     * Checks if the given object is not null and not empty.
     *
     * @param value the object to check (can be a String, Collection, Map, or Array)
     * @return true if the object is not null and not empty, false otherwise
     */
    public static boolean isNotNullOrEmpty(Object value) {
        return switch (value) {
            case null -> false;
            case String s -> !s.isEmpty();
            case Collection collection -> !collection.isEmpty();
            case Map map -> !map.isEmpty();
            case Object[] objects -> objects.length > 0;
            default -> true;
        };
    }

    /**
     * Checks if all the given objects are not null.
     *
     * @param values the objects to check
     * @return true if all objects are not null, false otherwise
     */
    public static boolean isNotNullMultiple(Object... values) {
        if (values == null) {
            return false;
        }
        for (Object value : values) {
            if (value == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if all the given objects are not null and not empty.
     *
     * @param values the objects to check
     * @return true if all objects are not null and not empty, false otherwise
     */
    public static boolean isNotNullOrEmptyMultiple(Object... values) {
        if (values == null) {
            return false;
        }
        for (Object value : values) {
            if (!isNotNullOrEmpty(value)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the given object is not null and not zero (for numeric values).
     *
     * @param value the object to check
     * @return true if the object is not null and not zero, false otherwise
     */
    public static boolean isNotNullOrZero(Object value) {
        if (value == null) {
            return false;
        }
        if (value instanceof Number) {
            return ((Number) value).doubleValue() != 0.0;
        }
        return true;
    }

    /**
     * Checks if the given string is empty.
     *
     * @param value the string to check
     * @return true if the string is empty, false otherwise
     */
    public static boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }

    /**
     * Checks if the given string is not empty.
     *
     * @param value the string to check
     * @return true if the string is not empty, false otherwise
     */
    public static boolean isNotEmpty(String value) {
        return value != null && !value.isEmpty();
    }

    /**
     * Checks if the given object is null.
     *
     * @param value the object to check
     * @return true if the object is null, false otherwise
     */
    public static boolean isNull(Object value) {
        return value == null;
    }

    /**
     * Checks if all the given objects are null.
     *
     * @param values the objects to check
     * @return true if all objects are null, false otherwise
     */
    public static boolean isNullMultiple(Object... values) {
        if (values == null) {
            return true;
        }
        for (Object value : values) {
            if (value != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if all the given objects are null or empty.
     *
     * @param values the objects to check
     * @return true if all objects are null or empty, false otherwise
     */
    public static boolean isNullOrEmptyMultiple(Object... values) {
        if (values == null) {
            return true;
        }
        for (Object value : values) {
            if (isNotNullOrEmpty(value)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the given object is null or zero (for numeric values).
     *
     * @param value the object to check
     * @return true if the object is null or zero, false otherwise
     */
    public static boolean isNullOrZero(Object value) {
        return value == null || (value instanceof Number && ((Number) value).doubleValue() == 0.0);
    }

    /**
     * Checks if the given object is null or empty.
     *
     * @param value the object to check (can be a String, Collection, Map, or Array)
     * @return true if the object is null or empty, false otherwise
     */
    public static boolean isNullOrEmpty(Object value) {
        return !isNotNullOrEmpty(value);
    }

    /**
     * Checks if two arrays have the same size.
     *
     * @param array1 the first array
     * @param array2 the second array
     * @return true if both arrays have the same size, false otherwise
     */
    public static boolean isArraySameSize(Object[] array1, Object[] array2) {
        if (array1 == null || array2 == null) {
            return false;
        }
        return array1.length == array2.length;
    }

}