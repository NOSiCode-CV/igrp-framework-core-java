package cv.igrp.framework.core.data;

public record EnumItem<T>(
        T value,
        String label
) {
}
