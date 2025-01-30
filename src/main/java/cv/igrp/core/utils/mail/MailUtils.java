package cv.igrp.core.utils.mail;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.io.File;
import java.util.List;
import java.util.Properties;
import jakarta.activation.*;

public class MailUtils {

    /**
     * Sends a simple email with no attachments.
     *
     * @param from    the sender's email address
     * @param to      the recipient's email address
     * @param subject the subject of the email
     * @param msg     the body of the email
     * @param replyTo the reply-to email address
     * @return true if the email was sent successfully, false otherwise
     */
    public static boolean sendMail(String from, String to, String subject, String msg, String replyTo, Properties customConfig) {
        try {
            Session session = createMailSession(customConfig);
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(msg);
            message.setReplyTo(new Address[] { new InternetAddress(replyTo) });

            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Sends an email with attachments.
     *
     * @param from     the sender's email address
     * @param to       the recipient's email address
     * @param subject  the subject of the email
     * @param msg      the body of the email
     * @param charset  the character set (e.g., UTF-8)
     * @param mimetype the MIME type of the email content (e.g., "text/html")
     * @param attachs  the files to attach
     * @param replyTo  the reply-to email address
     * @return true if the email was sent successfully, false otherwise
     */
    public static boolean sendMail(String from, String to, String subject, String msg, String charset, String mimetype, File[] attachs, String replyTo, Properties customConfig) {
        try {
            Session session = createMailSession(customConfig);
            MimeMessage message = new MimeMessage(session);
            MimeMultipart multipart = new MimeMultipart();

            // Set the email body
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setContent(msg, mimetype);
            multipart.addBodyPart(textPart);

            // Attach files
            if (attachs != null) {
                for (File file : attachs) {
                    MimeBodyPart attachmentPart = new MimeBodyPart();
                    DataSource source = new FileDataSource(file);
                    attachmentPart.setDataHandler(new DataHandler(source));
                    attachmentPart.setFileName(file.getName());
                    multipart.addBodyPart(attachmentPart);
                }
            }

            // Finalize the message
            message.setFrom(new InternetAddress(from));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setReplyTo(new Address[] { new InternetAddress(replyTo) });
            message.setContent(multipart);

            Transport.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Sends an email with custom configuration and multiple recipients.
     *
     * @param from             the sender's email address
     * @param to               the recipient's email address
     * @param subject          the subject of the email
     * @param msg              the body of the email
     * @param charset          the character set (e.g., UTF-8)
     * @param mimetype         the MIME type of the email content (e.g., "text/html")
     * @param attachs          the files to attach
     * @param replyTo          the reply-to email address
     * @param multiplerecipients true if multiple recipients are used
     * @param customConfig     custom properties (e.g., SMTP server settings)
     * @return true if the email was sent successfully, false otherwise
     */
    public static boolean sendMail(String from, String to, String subject, String msg, String charset, String mimetype, File[] attachs, String replyTo, boolean multiplerecipients, Properties customConfig) {
        try {
            Session session = createMailSession(customConfig);
            MimeMessage message = new MimeMessage(session);
            MimeMultipart multipart = new MimeMultipart();

            // Set the email body
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setContent(msg, mimetype);
            multipart.addBodyPart(textPart);

            // Attach files
            if (attachs != null) {
                for (File file : attachs) {
                    MimeBodyPart attachmentPart = new MimeBodyPart();
                    DataSource source = new FileDataSource(file);
                    attachmentPart.setDataHandler(new DataHandler(source));
                    attachmentPart.setFileName(file.getName());
                    multipart.addBodyPart(attachmentPart);
                }
            }

            // Finalize the message
            message.setFrom(new InternetAddress(from));
            if (multiplerecipients) {
                String[] recipients = to.split(",");
                for (String recipient : recipients) {
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
                }
            } else {
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            }
            message.setSubject(subject);
            message.setReplyTo(new Address[] { new InternetAddress(replyTo) });
            message.setContent(multipart);

            Transport.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Creates a mail session using default properties or custom properties if provided.
     *
     * @param customConfig custom properties (SMTP settings)
     * @return a Session object for sending the email
     */
    private static Session createMailSession(Properties customConfig) {
        Properties properties = customConfig != null ? customConfig : getDefaultMailProperties();
        return Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("username", "password");  // Use your credentials here
            }
        });
    }

    /**
     * Creates a default mail session with basic SMTP settings.
     *
     * @return the default mail properties
     */
    private static Properties getDefaultMailProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.example.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        return properties;
    }

    /**
     * Sends an email through a custom gateway (e.g., an external service).
     *
     * @param endpoint           the endpoint for the email gateway
     * @param httpAuthorizationHeaderValue the authorization header for the gateway
     * @param payload            the email payload
     * @param errors             a list to collect errors (if any)
     * @return true if the email was sent successfully, false otherwise
     */
    public static boolean sendMailGateway(String endpoint, String httpAuthorizationHeaderValue, Object payload, List<String> errors) {
        // Logic to interact with an external email gateway
        try {
            // Send email to the external service (implementation depends on the service)
            return true;
        } catch (Exception e) {
            errors.add(e.getMessage());
            return false;
        }
    }

}
