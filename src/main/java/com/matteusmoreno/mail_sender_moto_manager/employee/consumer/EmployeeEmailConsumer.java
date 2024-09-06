package com.matteusmoreno.mail_sender_moto_manager.employee.consumer;

import com.matteusmoreno.mail_sender_moto_manager.employee.request.CreateEmailEmployeeRequest;
import com.matteusmoreno.mail_sender_moto_manager.employee.request.EnableAndDisableEmailEmployeeRequest;
import com.matteusmoreno.mail_sender_moto_manager.employee.request.UpdateEmailEmployeeRequest;
import com.matteusmoreno.mail_sender_moto_manager.employee.service.EmployeeEmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeEmailConsumer {

    private final EmployeeEmailService employeeEmailService;

    @Autowired
    public EmployeeEmailConsumer(EmployeeEmailService employeeEmailService) {
        this.employeeEmailService = employeeEmailService;
    }

    @RabbitListener(queues = "create.employee.queue")
    public void onEmployeeCreated(CreateEmailEmployeeRequest request) {
        employeeEmailService.sendEmployeeCreationEmail(request);
    }

    @RabbitListener(queues = "update.employee.queue")
    public void onEmployeeUpdated(UpdateEmailEmployeeRequest request) {
        employeeEmailService.sendEmployeeUpdateEmail(request);
    }

    @RabbitListener(queues = "disable.employee.queue")
    public void onEmployeeDisabled(EnableAndDisableEmailEmployeeRequest request) {
        employeeEmailService.sendEmployeeDeactivationEmail(request);
    }

    @RabbitListener(queues = "enable.employee.queue")
    public void onEmployeeEnabled(EnableAndDisableEmailEmployeeRequest request) {
        employeeEmailService.sendEmployeeActivationEmail(request);
    }
}
