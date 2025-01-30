package cv.igrp.core.utils.collection;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ListUtils {

    /**
     * Checks if a list is null or empty.
     *
     * @param list the list to check
     * @param <T>  the type of the list elements
     * @return true if the list is null or empty, false otherwise
     */
    public static <T> boolean isNullOrEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }

    /**
     * Checks if a list is not null and contains elements.
     *
     * @param list the list to check
     * @param <T>  the type of the list elements
     * @return true if the list is not null and contains elements, false otherwise
     */
    public static <T> boolean isNotNullOrEmpty(List<T> list) {
        return list != null && !list.isEmpty();
    }

    /**
     * Converts a list of any type to a list of strings by calling String.valueOf() on each element.
     *
     * @param list the list to convert
     * @param <T>  the type of the list elements
     * @return a list of strings representing each element in the input list
     */
    public static <T> List<String> convertListToStringList(List<T> list) {
        if (list == null) {
            return Collections.emptyList();
        }
        return list.stream()
                .map(String::valueOf)
                .collect(Collectors.toList());
    }

    /**
     * Merges two lists into one, preserving the order of elements in both lists.
     *
     * @param list1 the first list
     * @param list2 the second list
     * @param <T>   the type of the list elements
     * @return a new list containing elements from both list1 and list2
     */
    public static <T> List<T> mergeLists(List<T> list1, List<T> list2) {
        if (list1 == null) list1 = new ArrayList<>();
        if (list2 == null) list2 = new ArrayList<>();
        List<T> mergedList = new ArrayList<>(list1);
        mergedList.addAll(list2);
        return mergedList;
    }

    /**
     * Removes duplicate elements from a list.
     *
     * @param list the list to remove duplicates from
     * @param <T>  the type of the list elements
     * @return a new list with duplicates removed
     */
    public static <T> List<T> removeDuplicates(List<T> list) {
        if (list == null) {
            return Collections.emptyList();
        }
        return list.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * Finds and returns the first duplicate element in a list.
     *
     * @param list the list to check for duplicates
     * @param <T>  the type of the list elements
     * @return the first duplicate element, or null if no duplicates are found
     */
    public static <T> T findFirstDuplicate(List<T> list) {
        if (list == null || list.size() < 2) {
            return null;
        }
        Set<T> seen = new HashSet<>();
        for (T element : list) {
            if (!seen.add(element)) {
                return element; // First duplicate found
            }
        }
        return null; // No duplicates found
    }

    /**
     * Finds and returns all duplicate elements in a list.
     *
     * @param list the list to check for duplicates
     * @param <T>  the type of the list elements
     * @return a list containing all duplicate elements
     */
    public static <T> List<T> findAllDuplicates(List<T> list) {
        if (list == null || list.size() < 2) {
            return Collections.emptyList();
        }
        Set<T> seen = new HashSet<>();
        Set<T> duplicates = new HashSet<>();
        for (T element : list) {
            if (!seen.add(element)) {
                duplicates.add(element); // Add to duplicates set if already seen
            }
        }
        return new ArrayList<>(duplicates);
    }

    /**
     * Checks if a list contains any null elements.
     *
     * @param list the list to check
     * @param <T>  the type of the list elements
     * @return true if the list contains null elements, false otherwise
     */
    public static <T> boolean containsNull(List<T> list) {
        return list != null && list.contains(null);
    }

    /**
     * Finds the index of the first occurrence of a specific element in the list.
     *
     * @param list    the list to search in
     * @param element the element to find
     * @param <T>     the type of the list elements
     * @return the index of the first occurrence of the element, or -1 if the element is not found
     */
    public static <T> int indexOf(List<T> list, T element) {
        if (list == null) {
            return -1;
        }
        return list.indexOf(element);
    }

    /**
     * Converts a list of key-value pairs (Map.Entry) to a map.
     *
     * @param entries the list of Map.Entry elements
     * @param <K>     the type of the keys
     * @param <V>     the type of the values
     * @return a map containing the key-value pairs
     */
    public static <K, V> Map<K, V> toMap(List<Map.Entry<K, V>> entries) {
        if (entries == null) {
            return Collections.emptyMap();
        }
        return entries.stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Filters a list based on a predicate and returns the filtered list.
     *
     * @param list      the list to filter
     * @param predicate the condition used to filter the list
     * @param <T>       the type of the list elements
     * @return a new list containing only the elements that satisfy the predicate
     */
    public static <T> List<T> filter(List<T> list, Predicate<? super T> predicate) {
        if (list == null) {
            return Collections.emptyList();
        }
        return list.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    /**
     * Converts a list of objects to a set, removing duplicates.
     *
     * @param list the list to convert
     * @param <T>  the type of the list elements
     * @return a set containing the unique elements from the list
     */
    public static <T> Set<T> toSet(List<T> list) {
        if (list == null) {
            return Collections.emptySet();
        }
        return new HashSet<>(list);
    }
}
