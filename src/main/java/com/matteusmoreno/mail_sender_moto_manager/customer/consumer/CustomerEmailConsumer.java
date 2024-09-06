package com.matteusmoreno.mail_sender_moto_manager.customer.consumer;

import com.matteusmoreno.mail_sender_moto_manager.customer.request.CreateEmailCustomerRequest;
import com.matteusmoreno.mail_sender_moto_manager.customer.request.EnableAndDisableEmailCustomerRequest;
import com.matteusmoreno.mail_sender_moto_manager.customer.request.UpdateEmailCustomerRequest;
import com.matteusmoreno.mail_sender_moto_manager.customer.service.CustomerEmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class CustomerEmailConsumer {

    private final CustomerEmailService customerEmailService;

    @Autowired
    public CustomerEmailConsumer(CustomerEmailService customerEmailService) {
        this.customerEmailService = customerEmailService;
    }

    @RabbitListener(queues = "create.customer.queue")
    public void onCustomerCreated(@Payload CreateEmailCustomerRequest request) {
        customerEmailService.sendCustomerCreationEmail(request);
    }

    @RabbitListener(queues = "update.customer.queue")
    public void onCustomerUpdated(@Payload UpdateEmailCustomerRequest request) {
        customerEmailService.sendCustomerUpdateEmail(request);
    }

    @RabbitListener(queues = "disable.customer.queue")
    public void onCustomerDisabled(@Payload EnableAndDisableEmailCustomerRequest request) {
        customerEmailService.sendCustomerDeactivationEmail(request);
    }

    @RabbitListener(queues = "enable.customer.queue")
    public void onCustomerEnabled(@Payload EnableAndDisableEmailCustomerRequest request) {
        customerEmailService.sendCustomerActivationEmail(request);
    }
}