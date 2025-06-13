package cv.igrp.framework.core.utils.object;

import cv.igrp.framework.core.data.EnumItem;
import cv.igrp.framework.core.domain.IgrpEnum;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Utility class for working with enums that implement the {@link IgrpEnum} interface.
 * <p>
 * Provides helper methods to convert enum constants into data transfer objects (DTOs).
 */
public class EnumUtils {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private EnumUtils() {
    }

    /**
     * Converts the constants of an enum class that implements {@link IgrpEnum} into a list of {@link EnumItem} objects.
     * <p>
     * This is typically used to expose enum values in a format suitable for REST APIs or UI components such as dropdowns.
     *
     * @param enumClazz the enum class to convert
     * @param <E>       the enum type that implements {@code IgrpEnum<V>}
     * @param <V>       the type of the value/code returned by {@code getCode()}
     * @return a list of {@code EnumItem<V>} containing the value-label pairs from the enum
     * @throws NullPointerException if {@code enumClazz} is null
     */
    public static <E extends Enum<E> & IgrpEnum<V>, V> List<EnumItem<V>> mapEnumToItems(Class<E> enumClazz) {
        Objects.requireNonNull(enumClazz, "enumClazz must not be null");
        return Arrays.stream(enumClazz.getEnumConstants())
                .map(e -> new EnumItem<>(e.getCode(), e.getDescription()))
                .toList();
    }
}

