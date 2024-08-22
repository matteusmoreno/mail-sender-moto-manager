package com.matteusmoreno.mail_sender_moto_manager.customer.controller;

import com.matteusmoreno.mail_sender_moto_manager.customer.request.CreateEmailCustomerRequest;
import com.matteusmoreno.mail_sender_moto_manager.customer.request.UpdateEmailCustomerRequest;
import com.matteusmoreno.mail_sender_moto_manager.customer.service.CustomerEmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class CustomerEmailController {

    private final CustomerEmailService customerEmailService;

    @Autowired
    public CustomerEmailController(CustomerEmailService customerEmailService) {
        this.customerEmailService = customerEmailService;
    }

    @PostMapping("/customer-creation")
    public ResponseEntity<Void> customerCreationEmail(@RequestBody @Valid CreateEmailCustomerRequest request) {
        customerEmailService.sendCustomerCreationEmail(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/customer-update")
    public ResponseEntity<Void> customerUpdateEmail(@RequestBody @Valid UpdateEmailCustomerRequest request) {
        customerEmailService.sendCustomerUpdateEmail(request);
        return ResponseEntity.ok().build();
    }
}
