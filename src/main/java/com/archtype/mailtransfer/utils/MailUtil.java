package com.archtype.mailtransfer.utils;

import com.archtype.mailtransfer.model.MailFromAddr;
import com.archtype.mailtransfer.model.MailInfo;
import com.archtype.mailtransfer.model.MailToAddr;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.*;
import java.io.File;
import java.util.Properties;

/**
 * @author: Think
 * @date: 2019/9/15
 */
public class MailUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailUtil.class);

    public static MimeMessage createAttachMail(Session session, MailFromAddr from, MailToAddr to, MailInfo mailInfo, String filePath) throws Exception {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from.getUsername()));
        message.setRecipient(RecipientType.TO, new InternetAddress(to.getTo()));
        message.setSubject(mailInfo.getSubject());
        MimeBodyPart text = new MimeBodyPart();
        text.setContent(mailInfo.getContent(), "text/html;charset=UTF-8");
        MimeMultipart mp = new MimeMultipart();
        mp.addBodyPart(text);
        mp.setSubType("mixed");
        if (StringUtils.isNotBlank(filePath)) {
            MimeBodyPart attach = new MimeBodyPart();
            DataHandler dh = new DataHandler(new FileDataSource(filePath.trim()));
            attach.setDataHandler(dh);
            String fileName = (new File(filePath.trim())).getName();
            attach.setFileName(MimeUtility.encodeWord(fileName, "UTF-8", (String) null));
            mp.addBodyPart(attach);
        }

        message.setContent(mp);
        message.saveChanges();
        return message;
    }

    public static boolean send(MailToAddr toAddr, final MailFromAddr fromAddr, MailInfo mailInfo, String filePath) throws MessagingException {
        Properties prop = new Properties();
        prop.setProperty("mail.host", fromAddr.getHost());
        prop.setProperty("mail.smtp.port", "25");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromAddr.getUsername(), fromAddr.getPassword());
            }
        });
        try {
            Message message = createAttachMail(session, fromAddr, toAddr, mailInfo, filePath);
            Transport.send(message);
            return true;
        } catch (Exception var7) {
            var7.printStackTrace();
            return false;
        }
    }
}
