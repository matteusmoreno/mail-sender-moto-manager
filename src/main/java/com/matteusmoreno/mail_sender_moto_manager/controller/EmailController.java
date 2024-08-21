package com.matteusmoreno.mail_sender_moto_manager.controller;

import com.matteusmoreno.mail_sender_moto_manager.request.EmailSendRequest;
import com.matteusmoreno.mail_sender_moto_manager.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/employee-creation")
    public ResponseEntity<Void> employeeCreationEmail(@RequestBody @Valid EmailSendRequest request) {
        emailService.sendEmployeeCreationEmail(request);
        return ResponseEntity.ok().build();
    }
}
