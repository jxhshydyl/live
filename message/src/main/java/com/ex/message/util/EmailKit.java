package com.ex.message.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.util.*;

public class EmailKit {
    private static Logger logger = LoggerFactory.getLogger(EmailKit.class);

    private static Session session;
    private MimeMessage mimeMessage;
    private String text;
    private String html;
    private List<MimeBodyPart> attachments = new ArrayList<MimeBodyPart>();

    public EmailKit(String emailHost, String emailPort, String protocol, Boolean sslEnable, String username, String password) {
        Properties props = new Properties();
        props.put("mail.transport.protocol", protocol);
        props.put("mail.smtp.host", emailHost);
        props.put("mail.smtp.port", emailPort);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", sslEnable ? "true" : "false");
        props.put("mail.debug", "false");
        props.put("mail.smtp.timeout", "10000");
        props.put("username", username);
        props.put("password", password);

        session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        this.mimeMessage = new MimeMessage(session);

    }


    /**
     * 设置邮件主题
     *
     * @param subject subject title
     * @return
     * @throws MessagingException
     */
    public EmailKit subject(String subject) throws MessagingException {
        this.mimeMessage.setSubject(subject, "UTF-8");
        return this;
    }


    /**
     * 设置邮件发送人和昵称
     *
     * @param nickName
     * @param from
     * @return
     * @throws MessagingException
     */
    public EmailKit from(String nickName, String from) throws MessagingException {
        try {
            nickName = MimeUtility.encodeText(nickName);
        } catch (Exception e) {
            logger.error("异常",e);
        }
        mimeMessage.setFrom(new InternetAddress(nickName + " <" + from + ">"));
        return this;
    }

    public EmailKit replyTo(String... replyTo) throws MessagingException {
        String result = Arrays.asList(replyTo).toString().replaceAll("(^\\[|\\]$)", "").replace(", ", ",");
        mimeMessage.setReplyTo(InternetAddress.parse(result));
        return this;
    }

    public EmailKit replyTo(String replyTo) throws MessagingException {
        mimeMessage.setReplyTo(InternetAddress.parse(replyTo.replace(";", ",")));
        return this;
    }

    public EmailKit to(String... to) throws Exception {
        return addRecipients(to, Message.RecipientType.TO);
    }

    public EmailKit to(String to) throws MessagingException {
        return addRecipient(to, Message.RecipientType.TO);
    }

    public EmailKit cc(String... cc) throws MessagingException {
        return addRecipients(cc, Message.RecipientType.CC);
    }

    public EmailKit cc(String cc) throws MessagingException {
        return addRecipient(cc, Message.RecipientType.CC);
    }

    public EmailKit bcc(String... bcc) throws MessagingException {
        return addRecipients(bcc, Message.RecipientType.BCC);
    }

    public EmailKit bcc(String bcc) throws MessagingException {
        return addRecipient(bcc, Message.RecipientType.BCC);
    }

    private EmailKit addRecipients(String[] recipients, Message.RecipientType type) throws MessagingException {
        String result = Arrays.asList(recipients).toString().replace("(^\\[|\\]$)", "").replace(", ", ",");
        mimeMessage.setRecipients(type, InternetAddress.parse(result));
        return this;
    }

    private EmailKit addRecipient(String recipient, Message.RecipientType type) throws MessagingException {
        mimeMessage.setRecipients(type, InternetAddress.parse(recipient.replace(";", ",")));
        return this;
    }

    public EmailKit text(String text) {
        this.text = text;
        return this;
    }

    public EmailKit html(String html) {
        this.html = html;
        return this;
    }

    public EmailKit attach(File file) throws MessagingException {
        attachments.add(createAttachment(file, null));
        return this;
    }

    public EmailKit attach(File file, String fileName) throws MessagingException {
        attachments.add(createAttachment(file, fileName));
        return this;
    }

    private MimeBodyPart createAttachment(File file, String fileName) throws MessagingException {
        MimeBodyPart attachmentPart = new MimeBodyPart();
        FileDataSource fds = new FileDataSource(file);
        attachmentPart.setDataHandler(new DataHandler(fds));
        try {
            attachmentPart.setFileName(null == fileName ? MimeUtility.encodeText(fds.getName()) : MimeUtility.encodeText(fileName));
        } catch (Exception e) {
            logger.error("异常",e);
        }
        return attachmentPart;
    }


    private MimeBodyPart toBodyPart(MimeMultipart cover) throws MessagingException {
        MimeBodyPart wrap = new MimeBodyPart();
        wrap.setContent(cover);
        return wrap;
    }

    private MimeBodyPart textPart() throws MessagingException {
        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setText(text);
        return bodyPart;
    }

    private MimeBodyPart htmlPart() throws MessagingException {
        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(html, "text/html; charset=utf-8");
        return bodyPart;
    }


    public void send() throws MessagingException {
        if (text == null && html == null) {
            throw new NullPointerException("At least one context has to be provided: Text or Html");
        }

        MimeMultipart cover;
        boolean usingAlternative = false;
        boolean hasAttachments = attachments.size() > 0;

        if (text != null && html == null) {
            // TEXT ONLY
            cover = new MimeMultipart("mixed");
            cover.addBodyPart(textPart());
        } else if (text == null && html != null) {
            // HTML ONLY
            cover = new MimeMultipart("mixed");
            cover.addBodyPart(htmlPart());
        } else {
            // HTML + TEXT
            cover = new MimeMultipart("alternative");
            cover.addBodyPart(textPart());
            cover.addBodyPart(htmlPart());
            usingAlternative = true;
        }

        MimeMultipart content = cover;
        if (usingAlternative && hasAttachments) {
            content = new MimeMultipart("mixed");
            content.addBodyPart(toBodyPart(cover));
        }

        for (MimeBodyPart attachment : attachments) {
            content.addBodyPart(attachment);
        }

        mimeMessage.setContent(content);
        mimeMessage.setSentDate(new Date());
        Transport.send(mimeMessage);
    }


}
