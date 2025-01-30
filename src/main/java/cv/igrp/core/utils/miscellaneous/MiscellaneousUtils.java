package cv.igrp.core.utils.miscellaneous;

import java.util.UUID;

public class MiscellaneousUtils {

    /**
     * Generates a random UUID.
     *
     * @return a randomly generated UUID as a string
     */
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

}
