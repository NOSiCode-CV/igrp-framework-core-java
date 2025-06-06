package cv.igrp.framework.core.utils.object;

import cv.igrp.framework.core.data.EnumDto;
import cv.igrp.framework.core.domain.IgrpEnum;

import java.util.Arrays;
import java.util.List;

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
     * Converts an enum class that implements {@link IgrpEnum} into a list of {@link EnumDto} objects.
     * <p>
     * Each enum constant is mapped to a DTO containing its code and description.
     *
     * @param enumClazz the enum class to convert
     * @param <T>       the enum type, which must extend {@link Enum} and implement {@link IgrpEnum}
     * @return a list of {@link EnumDto} instances representing the enum constants
     */
    public static <T extends Enum<T> & IgrpEnum> List<EnumDto> toEnumDto(Class<T> enumClazz) {
        return Arrays.stream(enumClazz.getEnumConstants())
                .map(e -> new EnumDto(e.getCode(), e.getDescription()))
                .toList();
    }
}
