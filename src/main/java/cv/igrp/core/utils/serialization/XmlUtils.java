package cv.igrp.core.utils.serialization;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringReader;
import java.io.StringWriter;

/**
 * Utility class for converting objects to XML and vice versa using JAXB.
 */
public class XmlUtils {

    final static Logger LOGGER = LoggerFactory.getLogger(XmlUtils.class);

    /**
     * Converts an XML string to a Java object of the specified class.
     *
     * @param xml the XML string to be converted
     * @param clazz the class type of the object to be converted to
     * @param <T> the type of the object
     * @return the Java object of the specified class, or null if conversion fails
     */
    public static <T> T fromXml(String xml, Class<T> clazz) {
        if (xml == null || clazz == null) {
            return null; // Return null if the XML or class is null
        }
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader reader = new StringReader(xml);
            return (T) unmarshaller.unmarshal(reader); // Convert XML to Java object
        } catch (JAXBException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * Converts a Java object to an XML string.
     *
     * @param obj the Java object to be converted to XML
     * @return the XML string representation of the object, or null if conversion fails
     */
    public static String toXml(Object obj) {
        if (obj == null) {
            return null; // Return null if the object is null
        }
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);
            return writer.toString();
        } catch (JAXBException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }
}
