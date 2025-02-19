package cv.igrp.framework.core.utils.object;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MapUtils {

    /**
     * Converts two arrays into a map, where elements from the first array are the keys
     * and elements from the second array are the values.
     *
     * @param array1 the first array (keys)
     * @param array2 the second array (values)
     * @return a map where keys are from array1 and values are from array2
     */
    public static Map<Object, Object> mapArray(Object[] array1, Object[] array2) {
        if (array1 == null || array2 == null || array1.length != array2.length) {
            throw new IllegalArgumentException("Arrays must not be null and must have the same length.");
        }

        Map<Object, Object> map = new HashMap<>();
        for (int i = 0; i < array1.length; i++) {
            map.put(array1[i], array2[i]);
        }
        return map;
    }

    /**
     * Converts two arrays into a map with a filtering condition. The filter is applied to the index
     * of the elements and can exclude certain key-value pairs.
     *
     * @param array1 the first array (keys)
     * @param array2 the second array (values)
     * @param filter a predicate that filters elements based on the index
     * @return a map where keys are from array1 and values are from array2, filtered by the predicate
     */
    public static Map<Object, Object> mapArray(Object[] array1, Object[] array2, Predicate<? super Integer> filter) {
        if (array1 == null || array2 == null || array1.length != array2.length) {
            throw new IllegalArgumentException("Arrays must not be null and must have the same length.");
        }

        Map<Object, Object> map = new HashMap<>();
        for (int i = 0; i < array1.length; i++) {
            if (filter.test(i)) {
                map.put(array1[i], array2[i]);
            }
        }
        return map;
    }

    /**
     * Converts a list of objects to a map using two fields from each object as the key and value.
     *
     * @param values    the list of objects to convert
     * @param keyField  the field name to be used as the key
     * @param valueField the field name to be used as the value
     * @return a map where the keys are values from the keyField and the values are from valueField
     */
    public static Map<?, ?> toMap(List<?> values, String keyField, String valueField) {
        if (values == null || keyField == null || valueField == null) {
            throw new IllegalArgumentException("Arguments must not be null.");
        }

        return values.stream()
                .collect(Collectors.toMap(
                        item -> getFieldValue(item, keyField),
                        item -> getFieldValue(item, valueField)
                ));
    }

    /**
     * Converts a list of objects to a map using two fields from each object as the key and value,
     * with an optional prompt for key and value retrieval.
     *
     * @param values    the list of objects to convert
     * @param keyField  the field name to be used as the key
     * @param valueField the field name to be used as the value
     * @param prompt    a string prompt used to handle key and value field retrieval
     * @return a map where the keys are values from the keyField and the values are from valueField
     */
    public static Map<Object, Object> toMap(List<?> values, String keyField, String valueField, String prompt) {
        if (values == null || keyField == null || valueField == null) {
            throw new IllegalArgumentException("Arguments must not be null.");
        }

        return values.stream()
                .collect(Collectors.toMap(
                        item -> getFieldValueWithPrompt(item, keyField, prompt),
                        item -> getFieldValueWithPrompt(item, valueField, prompt)
                ));
    }

    // Helper method to get the field value from an object
    private static Object getFieldValue(Object item, String field) {
        try {
            return item.getClass().getDeclaredField(field).get(item);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error retrieving field value for " + field, e);
        }
    }

    // Helper method to get the field value with an optional prompt (for handling key/value differently)
    private static Object getFieldValueWithPrompt(Object item, String field, String prompt) {
        // You can extend this method to implement prompt-based logic if needed
        return getFieldValue(item, field);
    }

}