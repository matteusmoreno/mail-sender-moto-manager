package com.matteusmoreno.mail_sender_moto_manager.controller;

import com.matteusmoreno.mail_sender_moto_manager.request.CreateEmailEmployeeRequest;
import com.matteusmoreno.mail_sender_moto_manager.request.UpdateEmailEmployeeRequest;
import com.matteusmoreno.mail_sender_moto_manager.service.EmployeeEmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmployeeEmailService employeeEmailService;

    @Autowired
    public EmailController(EmployeeEmailService employeeEmailService) {
        this.employeeEmailService = employeeEmailService;
    }

    @PostMapping("/employee-creation")
    public ResponseEntity<Void> employeeCreationEmail(@RequestBody @Valid CreateEmailEmployeeRequest request) {
        employeeEmailService.sendEmployeeCreationEmail(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/employee-update")
    public ResponseEntity<Void> employeeUpdateEmail(@RequestBody @Valid UpdateEmailEmployeeRequest request) {
        employeeEmailService.sendEmployeeUpdateEmail(request);
        return ResponseEntity.ok().build();
    }
}
