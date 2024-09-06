package com.matteusmoreno.mail_sender_moto_manager.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    //EXCHANGE
    @Bean
    public DirectExchange customerEmailExchange() {
        return new DirectExchange("customer.email.exchange");
    }

    @Bean
    public DirectExchange employeeEmailExchange() {
        return new DirectExchange("employee.email.exchange");
    }

    //CUSTOMER QUEUES
    @Bean
    public Queue createCustomerQueue() {
        return new Queue("create.customer.queue", true);
    }

    @Bean
    public Queue updateCustomerQueue() {
        return new Queue("update.customer.queue", true);
    }

    @Bean
    public Queue disableCustomerQueue() {
        return new Queue("disable.customer.queue", true);
    }

    @Bean
    public Queue enableCustomerQueue() {
        return new Queue("enable.customer.queue", true);
    }

    //CUSTOMER BINDINGS
    @Bean
    public Binding createCustomerBinding(Queue createCustomerQueue, DirectExchange customerEmailExchange) {
        return BindingBuilder.bind(createCustomerQueue).to(customerEmailExchange).with("customer.created");
    }

    @Bean
    public Binding updateCustomerBinding(Queue updateCustomerQueue, DirectExchange customerEmailExchange) {
        return BindingBuilder.bind(updateCustomerQueue).to(customerEmailExchange).with("customer.updated");
    }

    @Bean
    public Binding disableCustomerBinding(Queue disableCustomerQueue, DirectExchange customerEmailExchange) {
        return BindingBuilder.bind(disableCustomerQueue).to(customerEmailExchange).with("customer.disabled");
    }

    @Bean
    public Binding enableCustomerBinding(Queue enableCustomerQueue, DirectExchange customerEmailExchange) {
        return BindingBuilder.bind(enableCustomerQueue).to(customerEmailExchange).with("customer.enabled");
    }

    //EMPLOYEE QUEUES
    @Bean
    public Queue createEmployeeQueue() {
        return new Queue("create.employee.queue", true);
    }

    @Bean
    public Queue updateEmployeeQueue() {
        return new Queue("update.employee.queue", true);
    }

    @Bean
    public Queue disableEmployeeQueue() {
        return new Queue("disable.employee.queue", true);
    }

    @Bean
    public Queue enableEmployeeQueue() {
        return new Queue("enable.employee.queue", true);
    }

    //EMPLOYEE BINDINGS
    @Bean
    public Binding createEmployeeBinding(Queue createEmployeeQueue, DirectExchange employeeEmailExchange) {
        return BindingBuilder.bind(createEmployeeQueue).to(employeeEmailExchange).with("employee.created");
    }

    @Bean
    public Binding updateEmployeeBinding(Queue updateEmployeeQueue, DirectExchange employeeEmailExchange) {
        return BindingBuilder.bind(updateEmployeeQueue).to(employeeEmailExchange).with("employee.updated");
    }

    @Bean
    public Binding disableEmployeeBinding(Queue disableEmployeeQueue, DirectExchange employeeEmailExchange) {
        return BindingBuilder.bind(disableEmployeeQueue).to(employeeEmailExchange).with("employee.disabled");
    }

    @Bean
    public Binding enableEmployeeBinding(Queue enableEmployeeQueue, DirectExchange employeeEmailExchange) {
        return BindingBuilder.bind(enableEmployeeQueue).to(employeeEmailExchange).with("employee.enabled");
    }

    //MESSAGE CONVERTER
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
