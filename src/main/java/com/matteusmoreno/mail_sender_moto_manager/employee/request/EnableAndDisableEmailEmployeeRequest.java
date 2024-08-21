package com.matteusmoreno.mail_sender_moto_manager.employee.request;

public record EnableAndDisableEmailEmployeeRequest(
        String to,
        String id,
        String employeeName,
        String username,
        String cpf,
        String role) {
}
