package com.matteusmoreno.mail_sender_moto_manager.employee.request;

public record CreateEmailEmployeeRequest(
        String to,
        String id,
        String employeeName,
        String username,
        String password,
        String phone,
        String birthDate,
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
