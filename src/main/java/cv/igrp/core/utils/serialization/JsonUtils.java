package cv.igrp.core.utils.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;

public class JsonUtils {

    // Logger instance
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    private static final Gson gson = new Gson();

    /**
     * Converts a JSON string to an object of the specified type.
     *
     * @param json the JSON string to be converted
     * @param type the target type to convert the JSON string into
     * @return the object of the specified type
     */
    public static Object fromJson(String json, Type type) {
        if (json == null || type == null) {
            return null; // Return null if the input JSON or type is null
        }
        try {
            return gson.fromJson(json, type); // Deserialize JSON string to specified type
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null; // Return null if deserialization fails
        }
    }

    /**
     * Converts an object to its JSON string representation.
     *
     * @param object the object to be converted to JSON
     * @return the JSON string representation of the object
     */
    public static String toJson(Object object) {
        if (object == null) {
            return null; // Return null if the object is null
        }
        try {
            return gson.toJson(object); // Serialize object to JSON string
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null; // Return null if serialization fails
        }
    }

    // Convert an object to JSON with a custom builder (supports custom Gson builder registration)
    public static String toJsonWithJsonBuilder(Object object, JsonBuilder builder) {
        try {
            Gson gson = builder != null ? builder.create() : new Gson(); // If a builder is provided, use it
            return gson.toJson(object);
        } catch (Exception e) {
            LOGGER.error("Error converting object to JSON", e);
            return null;
        }
    }

    // JsonBuilder class for custom JSON serialization
    public static class JsonBuilder {
        private final GsonBuilder gsonBuilder;

        public JsonBuilder() {
            this.gsonBuilder = new GsonBuilder();
        }

        // Method to register a custom type adapter
        public JsonBuilder registerTypeAdapter(Object type, Object adapter) {
            gsonBuilder.registerTypeAdapter(type.getClass(), adapter);
            return this;
        }

        // Create the Gson instance with the registered customizations
        public Gson create() {
            return gsonBuilder.create();
        }
    }

}
