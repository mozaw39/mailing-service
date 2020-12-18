package org.service.mailing.utils;

import org.service.mailing.modal.SenderInfo;
import org.service.mailing.repository.AirtableRepository;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;

import java.util.*;

@Stateful
public class EmailUtils {
    @Inject
    AirtableRepository repository;

    List<Address> validSentAddresses = new ArrayList<Address>();

    public void setDefault() {
        this.validSentAddresses = new ArrayList<Address>();
    }

    public void mailService(SenderInfo senderInfo) {
        final String fromEmail = senderInfo.getAddress(); //requires valid gmail id
        final String password = senderInfo.getPassword(); // correct password for gmail id
        //get emails from airtable
        Map<String,String> dataFromAirTable = repository.getData();
        // Get the session object
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        Session session = Session.getDefaultInstance(prop, null);
        session.setDebug(true);
        Transport trans = null;
        try {
            trans = session.getTransport("smtp");
            trans.connect("smtp.gmail.com", fromEmail, password);
        /*if(trans != null)
        trans.addTransportListener(new TransportListener() {
            @Override
            public void messageDelivered(TransportEvent transportEvent) {
                validSentAddresses.addAll(Arrays.asList(transportEvent.getValidSentAddresses()));
            }

            @Override
            public void messageNotDelivered(TransportEvent transportEvent) {

            }

            @Override
            public void messagePartiallyDelivered(TransportEvent transportEvent) {
            }
        });*/
        // Compose message

        Transport finalTrans = trans;
        dataFromAirTable.forEach( (firstName, email) -> {
                    InternetAddress address = null;
            try {
                address = new InternetAddress(email);


            if (email != null && firstName!=null && address!=null && !validSentAddresses.contains(address)) {

                MimeMessage message = setMessage(session, fromEmail, firstName, senderInfo);
                finalTrans.sendMessage(message, new Address[]{address});
                System.out.println("EMail Sent Successfully with image!!");
                }
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            });
                }catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    private MimeMessage setMessage(Session session, String fromEmail, String firstName, SenderInfo senderInfo) throws MessagingException {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        //message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        message.setSubject(senderInfo.getSubject());
        String htmlMessage = "<html><p>Dear "+ firstName + ",</p><p>"+ senderInfo.getBody() + "</p>";
        htmlMessage += "<div style=\"display:inline-block;vertical-align:top;\"><img src=\"cid:image-id\" />\n" +
                "<div style=\"display:inline-block;border-left: 3px solid black;height: 150px;\"></div></div>";
        htmlMessage += "<div style=\"display:inline-block;\"><h3>" + senderInfo.getFirstName() + " " + senderInfo.getLastName()+ "</h3>";
        htmlMessage += "<h4>" + senderInfo.getPoste() + "</h4>";
        htmlMessage += "<a href=\"https://www.ym-africa.com/\">ym-africa</a></div>";
        htmlMessage += "</html>";
        String filename = "ym-africa-logo.png";
        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(htmlMessage, "text/html");
        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        // adds inline image attachments
        /*String contentId = "image-id";
        MimeBodyPart imagePart = new MimeBodyPart();
        imagePart.setHeader("Content-ID", "<" + contentId + ">");
        imagePart.setDisposition(MimeBodyPart.INLINE);
        String imageFilePath = System.getProperty("user.home") + "/" + filename;
        try {
            imagePart.attachFile(imageFilePath);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        multipart.addBodyPart(imagePart);*/
        //Sending a message with a img signature
        message.setContent(multipart);
        return message;
    }

}

