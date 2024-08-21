package com.matteusmoreno.mail_sender_moto_manager.employee.request;

public record UpdateEmailEmployeeRequest(
        String to,
        String id,
        String employeeName,
        String username,
        String phone,
        String birthDate,
        String age,
        String cpf,
        String role,
        String street,
        String number,
        String neighborhood,
        String city,
        String state,
        String zipcode,
        String complement) {
}
