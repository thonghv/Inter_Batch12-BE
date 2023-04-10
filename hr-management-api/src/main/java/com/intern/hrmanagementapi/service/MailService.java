package com.intern.hrmanagementapi.service;

import com.intern.hrmanagementapi.entity.Mail;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class MailService {
    private JavaMailSender javaMailSender;

    @Autowired
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(Mail m) throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(m.getEmailAddress());
        mail.setSubject("Testing Mail API");
        mail.setText("Hurray ! You have done that dude...");
        javaMailSender.send(mail);
    }
    public void sendEmailWithAttachment(Mail m, MultipartFile attachment) throws MailException, MessagingException, IOException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(m.getEmailAddress());
        helper.setSubject("Testing Mail API with Attachment");
        helper.setText("Please find the attached document below.");

        helper.addAttachment(attachment.getOriginalFilename(), new ByteArrayResource(attachment.getBytes()));

        javaMailSender.send(mimeMessage);
    }
}