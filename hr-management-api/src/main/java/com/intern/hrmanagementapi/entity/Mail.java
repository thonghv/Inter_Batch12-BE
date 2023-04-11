package com.intern.hrmanagementapi.entity;

import jakarta.persistence.Entity;
import org.springframework.stereotype.Component;

@Component
public class Mail {
    private String emailAddress;

    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setAttachmentName(String originalFilename) {
    }

    public void setAttachment(byte[] bytes) {
    }
}
