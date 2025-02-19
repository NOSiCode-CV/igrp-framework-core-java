package cv.igrp.framework.core.utils.datetime;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.Temporal;

public class DateTimeUtils {

    /**
     * Converts a date string from one format to another.
     *
     * @param date        the date string to be converted
     * @param formatIn    the input date format
     * @param outputFormat the desired output date format
     * @return the converted date string, or null if the input is invalid
     */
    public static String convertDate(String date, String formatIn, String outputFormat) {
        if (date == null || formatIn == null || outputFormat == null) {
            return null;
        }
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat(formatIn);
            SimpleDateFormat outputFormatDate = new SimpleDateFormat(outputFormat);
            return outputFormatDate.format(inputFormat.parse(date));
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Converts a date string to a Timestamp based on the provided format.
     *
     * @param date      the date string to be converted
     * @param formatIn  the format of the input date string
     * @return a Timestamp object representing the date, or null if the input is invalid
     */
    public static Timestamp toTimestamp(String date, String formatIn) {
        if (date == null || formatIn == null) {
            return null; // Return null for invalid input
        }
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat(formatIn);
            java.util.Date parsedDate = inputFormat.parse(date);
            return new Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            return null; // Return null if parsing fails
        }
    }

    /**
     * Converts a date string from one format to another and returns it as a java.sql.Date.
     *
     * @param data          the date string to be converted
     * @param inputFormat   the format of the input date string
     * @param outputFormat  the desired output date format
     * @return a java.sql.Date object representing the converted date, or null if the input is invalid
     */
    public static java.sql.Date formatDate(String data, String inputFormat, String outputFormat) {
        if (data == null || inputFormat == null || outputFormat == null) {
            return null; // Return null for invalid input
        }
        try {
            SimpleDateFormat inputFormatter = new SimpleDateFormat(inputFormat);
            SimpleDateFormat outputFormatter = new SimpleDateFormat(outputFormat);

            // Parse the input date and reformat it
            java.util.Date parsedDate = inputFormatter.parse(data);
            String reformattedDate = outputFormatter.format(parsedDate);

            // Convert the reformatted date back to java.sql.Date
            java.util.Date finalDate = outputFormatter.parse(reformattedDate);
            return new java.sql.Date(finalDate.getTime());
        } catch (ParseException e) {
            return null; // Return null if parsing fails
        }
    }

    /**
     * Gets the current date and time in the format dd/MM/yyyy HH:mm:ss.
     *
     * @return the current date and time as a formatted string
     */
    public static String getCurrentDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formatter.format(new java.util.Date());
    }

    /**
     * Gets the current date in the format dd/MM/yyyy.
     *
     * @return the current date as a formatted string
     */
    public static String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(new java.util.Date());
    }

    /**
     * Gets the current date in the specified output format.
     *
     * @param outputFormat the desired format for the current date
     * @return the current date as a formatted string, or null if the format is invalid
     */
    public static String getCurrentDate(String outputFormat) {
        if (outputFormat == null || outputFormat.isEmpty()) {
            return null; // Return null for invalid format
        }
        SimpleDateFormat formatter = new SimpleDateFormat(outputFormat);
        return formatter.format(new java.util.Date());
    }

    /**
     * Gets the current date as a java.sql.Date.
     *
     * @return the current date as a java.sql.Date object
     */
    public static java.sql.Date getCurrentDateSql() {
        return new java.sql.Date(System.currentTimeMillis());
    }

    /**
     * Gets the current year.
     *
     * @return the current year as an Integer
     */
    public static Integer getCurrentYear() {
        return java.time.Year.now().getValue();
    }

    /**
     * Gets the day of the month from a java.util.Date.
     *
     * @param date the date from which the day of the month is extracted
     * @return the day of the month as an Integer
     */
    public static Integer getDayFromDate(java.util.Date date) {
        if (date == null) {
            return null; // Return null if the date is null
        }
        java.time.LocalDate localDate = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        return localDate.getDayOfMonth();
    }

    /**
     * Gets the month of the year from a java.util.Date.
     *
     * @param date the date from which the month is extracted
     * @return the month of the year as an Integer (1 for January, 12 for December)
     */
    public static Integer getMonthFromDate(java.util.Date date) {
        if (date == null) {
            return null; // Return null if the date is null
        }
        java.time.LocalDate localDate = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        return localDate.getMonthValue();
    }

    /**
     * Gets the year from a java.util.Date.
     *
     * @param date the date from which the year is extracted
     * @return the year as an Integer
     */
    public static Integer getYearFromDate(java.util.Date date) {
        if (date == null) {
            return null; // Return null if the date is null
        }
        java.time.LocalDate localDate = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        return localDate.getYear();
    }

    /**
     * Gets the day of the month from a LocalDate or LocalDateTime.
     *
     * @param date the LocalDate or LocalDateTime from which the day is extracted
     * @return the day of the month as an Integer
     */
    public static Integer getDayFromDate(java.time.temporal.Temporal date) {
        return switch (date) {
            case null -> null; // Return null if the date is null
            case java.time.LocalDate localDate -> localDate.getDayOfMonth();
            case java.time.LocalDateTime localDateTime -> localDateTime.getDayOfMonth();
            default -> null;
        };
    }

    /**
     * Gets the month of the year from a LocalDate or LocalDateTime.
     *
     * @param date the LocalDate or LocalDateTime from which the month is extracted
     * @return the month of the year as an Integer (1 for January, 12 for December)
     */
    public static Integer getMonthFromDate(java.time.temporal.Temporal date) {
        return switch (date) {
            case null -> null; // Return null if the date is null
            case java.time.LocalDate localDate -> localDate.getMonthValue();
            case java.time.LocalDateTime localDateTime -> localDateTime.getMonthValue();
            default -> null;
        };
    }

    /**
     * Gets the year from a LocalDate or LocalDateTime.
     *
     * @param date the LocalDate or LocalDateTime from which the year is extracted
     * @return the year as an Integer
     */
    public static Integer getYearFromDate(java.time.temporal.Temporal date) {
        if (date == null) {
            return null; // Return null if the date is null
        }
        if (date instanceof java.time.LocalDate) {
            return ((java.time.LocalDate) date).getYear();
        } else if (date instanceof java.time.LocalDateTime) {
            return ((java.time.LocalDateTime) date).getYear();
        }
        return null; // Return null if the type is not LocalDate or LocalDateTime
    }

    /**
     * Converts a Temporal object (LocalDate, LocalDateTime, etc.) to a formatted string.
     *
     * @param <T> the type of Temporal object (LocalDate, LocalDateTime, etc.)
     * @param date the Temporal object to be formatted
     * @param formatter the DateTimeFormatter to be used for formatting
     * @return the formatted date as a string
     */
    public static <T extends Temporal> String parseDateToString(T date, DateTimeFormatter formatter) {
        if (date == null || formatter == null) {
            return null; // Return null if the date or formatter is null
        }
        return formatter.format(date); // Format the Temporal object using the provided formatter
    }

    /**
     * Converts a  Temporal object (LocalDate, LocalDateTime, etc.) to a formatted string.
     *
     * @param <T> the type of Temporal object (LocalDate, LocalDateTime, etc.)
     * @param outputFormatter the desired format for the output string
     * @return the formatted LocalDateTime as a string
     */
    public static <T extends Temporal>  String convertLocalDateTimeToString(T date, String outputFormatter) {
        if (date == null || outputFormatter == null || outputFormatter.isEmpty()) {
            return null; // Return null for invalid input
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(outputFormatter);
        return formatter.format(date); // Format the LocalDateTime using the provided formatter
    }

    /**
     * Converts a string into a Temporal object (LocalDate, LocalDateTime, ZonedDateTime) based on the provided format.
     *
     * @param <T> the type of Temporal object (LocalDate, LocalDateTime, ZonedDateTime, etc.)
     * @param dateStr the date string to be converted
     * @param formatter the DateTimeFormatter used to parse the string
     * @param temporalClass the class type of the Temporal object (LocalDate, LocalDateTime, ZonedDateTime)
     * @return the corresponding Temporal object or null if the conversion fails
     */
    public static <T extends Temporal> T convertStringToTemporal(String dateStr, String formatter, Class<T> temporalClass) {
        if (dateStr == null || formatter == null || formatter.isEmpty()) {
            return null; // Return null for invalid input
        }

        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatter);

            if (temporalClass.equals(LocalDate.class)) {
                return temporalClass.cast(LocalDate.parse(dateStr, dateTimeFormatter)); // Parse into LocalDate
            } else if (temporalClass.equals(LocalDateTime.class)) {
                return temporalClass.cast(LocalDateTime.parse(dateStr, dateTimeFormatter)); // Parse into LocalDateTime
            } else if (temporalClass.equals(ZonedDateTime.class)) {
                return temporalClass.cast(ZonedDateTime.parse(dateStr, dateTimeFormatter)); // Parse into ZonedDateTime
            } else {
                return null; // Unsupported Temporal class
            }
        } catch (DateTimeParseException e) {
            return null; // Return null if parsing fails
        }
    }



}
