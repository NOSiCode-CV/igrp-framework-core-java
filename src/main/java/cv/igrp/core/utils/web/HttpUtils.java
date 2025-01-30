package cv.igrp.core.utils.web;

import com.google.gson.Gson;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

public class HttpUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);
    private static final Gson gson = new Gson(); // Gson instance for JSON processing

    /**
     * Sends a GET request to the specified URL and parses the response into the given result type.
     *
     * @param url    the target URL
     * @param result the class type of the expected response
     * @param <T>    the type of the response object
     * @return the response as an object of the specified type
     */
    public static <T> T httpGet(String url, Class<T> result) {
        return httpGet(url, null, null, result);
    }

    /**
     * Sends a GET request to the specified URL with the given media type and parses the response.
     *
     * @param url       the target URL
     * @param mediaType the acceptable media types (e.g., "application/json")
     * @param result    the class type of the expected response
     * @param <T>       the type of the response object
     * @return the response as an object of the specified type
     */
    public static <T> T httpGet(String url, String[] mediaType, Class<T> result) {
        return httpGet(url, mediaType, null, result);
    }

    /**
     * Sends a GET request to the specified URL with headers and media types and parses the response.
     *
     * @param url         the target URL
     * @param mediaType   the acceptable media types (e.g., "application/json")
     * @param httpHeaders the headers to include in the request
     * @param result      the class type of the expected response
     * @param <T>         the type of the response object
     * @return the response as an object of the specified type
     */
    public static <T> T httpGet(String url, String[] mediaType, Map<String, Object> httpHeaders, Class<T> result) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);

            // Add headers if provided
            if (httpHeaders != null) {
                httpHeaders.forEach((key, value) -> request.addHeader(key, value.toString()));
            }

            // Add media type headers if provided
            if (mediaType != null) {
                for (String type : mediaType) {
                    request.addHeader("Accept", type);
                }
            }

            HttpClientResponseHandler<T> responseHandler = handleResponse(result);

            return client.execute(request, responseHandler);
        } catch (IOException e) {
            logger.error("Error executing HTTP GET request to URL: {}", url, e);
            return null;
        }
    }

    /**
     * Sends a POST request to the specified URL with headers and media types and parses the response.
     *
     * @param url             the target URL
     * @param content         the request body
     * @param mediaType       the acceptable media types (e.g., "application/json")
     * @param httpHeaders     the headers to include in the request
     * @param entityMediaType the media type of the request body
     * @param result          the class type of the expected response
     * @param <T>             the type of the response object
     * @return the response as an object of the specified type
     */
    public static <T> T httpPost(String url, Object content, String[] mediaType, Map<String, Object> httpHeaders, String entityMediaType, Class<T> result) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(url);

            // Add headers if provided
            if (httpHeaders != null) {
                httpHeaders.forEach((key, value) -> post.addHeader(key, value.toString()));
            }

            // Add media type headers if provided
            if (mediaType != null) {
                for (String type : mediaType) {
                    post.addHeader("Accept", type);
                }
            }

            // Set the request body if provided
            if (content != null) {
                StringEntity entity = new StringEntity(
                        gson.toJson(content),
                        ContentType.create(entityMediaType)
                );
                post.setEntity(entity);
            }

            HttpClientResponseHandler<T> responseHandler = handleResponse(result);

            return client.execute(post, responseHandler);
        } catch (IOException e) {
            logger.error("Error executing HTTP POST request to URL: {}", url, e);
            return null;
        }
    }

    /**
     * Sends a POST request to the specified URL with media types and parses the response.
     *
     * @param url             the target URL
     * @param content         the request body
     * @param mediaType       the acceptable media types (e.g., "application/json")
     * @param entityMediaType the media type of the request body
     * @param result          the class type of the expected response
     * @param <T>             the type of the response object
     * @return the response as an object of the specified type
     */
    public static <T> T httpPost(String url, Object content, String[] mediaType, String entityMediaType, Class<T> result) {
        return httpPost(url, content, mediaType, null, entityMediaType, result);
    }

    /**
     * Creates a {@link HttpClientResponseHandler} to process the HTTP response and convert it into the specified result type.
     *
     * @param <T>    the type of the object to which the response should be converted
     * @param result the class type of the expected response
     * @return a {@link HttpClientResponseHandler} that processes the response and converts it into the specified type
     *
     * <p>The response handler:
     * <ul>
     *   <li>Checks if the HTTP status code is in the range of 200 to 299 (successful responses).</li>
     *   <li>Reads the response entity if present and converts it to a string.</li>
     *   <li>Deserializes the string into the specified class type using Gson.</li>
     *   <li>Logs an error and returns {@code null} if the status code is outside the expected range or the entity is absent.</li>
     * </ul>
     *
     * <p>If an error occurs during response handling, it is logged using SLF4J.</p>
     *
     */
    private static <T> HttpClientResponseHandler<T> handleResponse(Class<T> result) {
        return response -> {
            int status = response.getCode();
            HttpEntity entity = response.getEntity();
            if (status >= 200 && status < 300 && entity != null) {
                String responseBody = new String(entity.getContent().readAllBytes());
                return gson.fromJson(responseBody, result);
            } else {
                logger.error("Unexpected response status: {}", status);
                return null;
            }
        };
    }

}
