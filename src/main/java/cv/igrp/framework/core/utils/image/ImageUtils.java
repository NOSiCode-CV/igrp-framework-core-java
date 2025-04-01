package cv.igrp.framework.core.utils.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * Utility class for image-related operations including conversion, compression, decompression, and validation.
 */
public class ImageUtils {

  /**
   * Converts a BufferedImage to a byte array in the specified format.
   *
   * @param image the BufferedImage to be converted
   * @param format the format of the image (e.g., "jpeg", "png")
   * @return byte array representing the image
   * @throws IOException if there is an error during image conversion
   */
  public static byte[] imageToByteArray(BufferedImage image, String format) throws IOException {
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    ImageIO.write(image, format, stream);

    return stream.toByteArray();
  }

  /**
   * Reads an image from the specified file path and returns it as a BufferedImage.
   *
   * @param path the file path of the image
   * @return BufferedImage representing the image
   * @throws IOException if there is an error during image reading
   */
  public static BufferedImage readImageFromResources(String path) throws IOException {
    BufferedImage img;
    img = ImageIO.read(new File(path));

    return img;
  }

  /**
   * Compresses the provided byte array using Deflater with the best compression level.
   *
   * @param data the byte array to be compressed
   * @return compressed byte array
   * @throws Exception if there is an error during compression
   */
  public static byte[] compressImage(byte[] data) throws Exception {
    Deflater deflater = new Deflater();
    deflater.setLevel(Deflater.BEST_COMPRESSION);
    deflater.setInput(data);
    deflater.finish();

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
    byte[] tmp = new byte[4 * 1024];
    while (!deflater.finished()) {
      int size = deflater.deflate(tmp);
      outputStream.write(tmp, 0, size);
    }

    outputStream.close();

    return outputStream.toByteArray();
  }

  /**
   * Decompresses the provided byte array using Inflater.
   *
   * @param data the compressed byte array
   * @return decompressed byte array
   * @throws DataFormatException if the data format is invalid
   * @throws IOException if there is an error during decompression
   */
  public static byte[] decompressImage(byte[] data) throws DataFormatException, IOException {
    Inflater inflater = new Inflater();
    inflater.setInput(data);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
    byte[] tmp = new byte[4 * 1024];

    while (!inflater.finished()) {
      int count = inflater.inflate(tmp);
      outputStream.write(tmp, 0, count);
    }
    outputStream.close();

    return outputStream.toByteArray();
  }

  /**
   * Checks if the provided byte array is a valid image.
   * This method loads the image to memory, so use with caution for large data.
   *
   * @param data the byte array representing the image
   * @return true if the data is a valid image, false otherwise
   */
  public static Boolean isImage(byte[] data) {
    InputStream input = new ByteArrayInputStream(data);

    try {
      // If read, then it's an image (only BMP, GIF, JPEG, PNG, TIFF, and WBMP are recognized).
      ImageIO.read(input).toString();
    } catch (Exception e) {
      // not an image
      return false;
    }

    return true;
  }
}