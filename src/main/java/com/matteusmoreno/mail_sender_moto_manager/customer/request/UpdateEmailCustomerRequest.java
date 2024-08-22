package com.matteusmoreno.mail_sender_moto_manager.customer.request;

public record UpdateEmailCustomerRequest(
        String to,
        String id,
        String customerName,
        String email,
        String birthDate,
        String age,
        String phone,
        String createdAt,
        String updatedAt) {
}
