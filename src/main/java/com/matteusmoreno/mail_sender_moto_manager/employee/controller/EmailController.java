package com.matteusmoreno.mail_sender_moto_manager.employee.controller;

import com.matteusmoreno.mail_sender_moto_manager.employee.request.CreateEmailEmployeeRequest;
import com.matteusmoreno.mail_sender_moto_manager.employee.request.EnableAndDisableEmailEmployeeRequest;
import com.matteusmoreno.mail_sender_moto_manager.employee.request.UpdateEmailEmployeeRequest;
import com.matteusmoreno.mail_sender_moto_manager.employee.service.EmployeeEmailService;
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

    @PostMapping("/employee-disable")
    public ResponseEntity<Void> employeeDisableEmail(@RequestBody @Valid EnableAndDisableEmailEmployeeRequest request) {
        employeeEmailService.sendEmployeeDeactivationEmail(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/employee-enable")
    public ResponseEntity<Void> employeeEnableEmail(@RequestBody @Valid EnableAndDisableEmailEmployeeRequest request) {
        employeeEmailService.sendEmployeeActivationEmail(request);
        return ResponseEntity.ok().build();
    }
}
