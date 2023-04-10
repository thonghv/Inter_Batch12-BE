package com.intern.hrmanagementapi.controller;

import com.intern.hrmanagementapi.constant.EndpointConst;
import com.intern.hrmanagementapi.constant.MessageConst;
import com.intern.hrmanagementapi.entity.Mail;
import com.intern.hrmanagementapi.model.DataResponseDto;
import com.intern.hrmanagementapi.service.MailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;
@RequestMapping(value = {EndpointConst.EMPLOYEE_BASE_PATH})
@RestController
public class MailController {

    @Autowired
    private MailService notificationService;

    @Autowired
    private Mail m;

    @Operation(security = {@SecurityRequirement(name = "bearer-key")})

    @PostMapping("send-mail")
    public ResponseEntity<?> send(@RequestParam String emailAddress) {
        m.setEmailAddress(emailAddress);
        try {
            notificationService.sendEmail(m);
        } catch (MailException mailException) {
            System.out.println(mailException);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(DataResponseDto.success(HttpStatus.OK.value(), MessageConst.SUCCESS, "Your mail has been sent to the user."));

    }
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})

//    @PostMapping("send-mail-attachment")
    @PostMapping(value = "send-mail-attachment", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String sendWithAttachment(@RequestParam String emailAddress,
                                    @RequestParam("file") MultipartFile file)
            throws MessagingException, IOException {
        m.setEmailAddress(emailAddress);
        m.setAttachmentName(file.getOriginalFilename());
        m.setAttachment(file.getBytes());
        try {
            notificationService.sendEmailWithAttachment(m,file);
        } catch (MailException mailException) {
            System.out.println(mailException);
        }
        return "Your mail has been send to the user.";
    }
}
