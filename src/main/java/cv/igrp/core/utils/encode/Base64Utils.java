package cv.igrp.core.utils.encode;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Utils {

    /**
     * Encodes a given string to Base64.
     *
     * @param anotherStr the input string to encode
     * @return the Base64-encoded string
     */
    public static String toBase64(String anotherStr) {
        if (anotherStr == null) {
            return null;
        }
        return Base64.getEncoder().encodeToString(anotherStr.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Encodes a given string to URL-safe Base64.
     *
     * @param str the input string to encode
     * @return the URL-safe Base64-encoded string
     */
    public static String toUrlEncodedBase64(String str) {
        if (str == null) {
            return null;
        }
        return Base64.getUrlEncoder().withoutPadding().encodeToString(str.getBytes(StandardCharsets.UTF_8));
    }

}
