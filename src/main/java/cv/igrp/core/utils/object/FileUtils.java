package cv.igrp.core.utils.object;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import jakarta.servlet.http.Part;
import org.apache.commons.io.IOUtils;

public class FileUtils {

    /**
     * Converts a byte array to a Base64 encoded string.
     *
     * @param bytes the byte array to encode
     * @return the Base64 encoded string
     */
    public static String convertInputStreamToBase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * Converts an InputStream to a Base64 encoded string.
     *
     * @param inputStream the InputStream to encode
     * @return the Base64 encoded string
     */
    public static String convertInputStreamToBase64(InputStream inputStream) {
        try {
            byte[] bytes = convertInputStreamToByte(inputStream);
            return Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert InputStream to Base64", e);
        }
    }

    /**
     * Converts an InputStream to a byte array.
     *
     * @param inputStream the InputStream to convert
     * @return the byte array representation of the InputStream
     * @throws IOException if an I/O error occurs
     */
    public static byte[] convertInputStreamToByte(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        IOUtils.copy(inputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * Converts a string to an InputStream.
     *
     * @param content the string to convert
     * @return the InputStream representation of the string
     */
    public static InputStream convertStringToInputStream(String content) {
        return new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Converts an InputStream to a string.
     *
     * @param inputStream the InputStream to convert
     * @return the string representation of the InputStream
     * @throws IOException if an I/O error occurs
     */
    public static String convertInputStreamToString(InputStream inputStream) throws IOException {
        return new String(convertInputStreamToByte(inputStream), StandardCharsets.UTF_8);
    }

    /**
     * Converts a Part to a byte array.
     *
     * @param part the file part
     * @return byte array of the uploaded file
     * @throws IOException if an I/O error occurs
     */
    public static byte[] convertPartToBytes(Part part) throws IOException {
        try (InputStream inputStream = part.getInputStream()) {
            return inputStream.readAllBytes();
        }
    }

    /**
     * Saves the uploaded file (Part) to a specified location.
     *
     * @param part       the file part to save
     * @param destination the path to save the file
     * @throws IOException if an I/O error occurs
     */
    public static void savePartToFile(Part part, String destination) throws IOException {
        part.write(destination);
    }

    /**
     * Extracts the file name from the Part.
     *
     * @param part the file part
     * @return the file name
     */
    public static String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        for (String cd : contentDisposition.split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.split("=")[1].trim().replace("\"", "");
            }
        }
        return null;
    }
}
