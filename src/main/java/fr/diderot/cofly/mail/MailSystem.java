package fr.diderot.cofly.mail;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSystem {

    //https://www.journaldev.com/2532/javamail-example-send-mail-in-java-smtp
    public void send(final String email, final String password,
            final List<String> cc, final String subject, final String body) {
        Properties props;
        Session session;
        Authenticator auth;

        props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        };

        session = Session.getInstance(props, auth);

        cc.forEach(to -> {
            sendEmail(session, to, subject, body);
        });
    }

    private void sendEmail(Session session, String emailTo, String subject,
            String body) {
        try {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("no_reply@example.com", "NoReply-JD"));

            msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

            msg.setSubject(subject, "UTF-8");

            msg.setText(body, "UTF-8");

            msg.setSentDate(covertLocalDateTime(LocalDateTime.now()));

            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(emailTo, false));

            Transport.send(msg);

        } catch (UnsupportedEncodingException | MessagingException ex) {
            ex.printStackTrace();
        }
    }

    private Date covertLocalDateTime(LocalDateTime ldt) {
        ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
        return Date.from(zdt.toInstant());
    }
}
