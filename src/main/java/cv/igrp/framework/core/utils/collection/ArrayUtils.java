package cv.igrp.framework.core.utils.collection;

import java.util.Arrays;

public class ArrayUtils {

    /**
     * Converts an array of objects of type N to a string array.
     *
     * @param array the input array to convert
     * @param <N>   the type of the elements in the input array
     * @return a string array containing the string representation of each element in the input array
     */
    public static <N> String[] convertArrayObjectToArrayString(N[] array) {
        if (array == null) {
            return new String[0]; // Return an empty array if the input is null
        }
        return Arrays.stream(array)
                .map(String::valueOf) // Convert each element to a String using valueOf()
                .toArray(String[]::new); // Collect results into a new String[] array
    }
}
