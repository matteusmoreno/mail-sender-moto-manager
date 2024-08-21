package com.matteusmoreno.mail_sender_moto_manager.customer.request;

public record CreateEmailCustomerRequest(
        String to,
        String id,
        String customerName,
        String birthDate,
        String age,
        String phone,
        String createdAt) {
}
