package com.matteusmoreno.mail_sender_moto_manager.request;

public record DisableEmailEmployeeRequest(
        String to,
        String id,
        String employeeName,
        String username,
        String cpf,
        String role) {
}
