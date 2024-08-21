package com.matteusmoreno.mail_sender_moto_manager.service;

import com.matteusmoreno.mail_sender_moto_manager.request.EmailSendRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Value("${SPRING_GMAIL_USERNAME}")
    private String hostEmail;

    public void sendEmployeeCreationEmail(EmailSendRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.to());
        message.setSubject("CONTA CRIADA COM SUCESSO");
        message.setFrom(hostEmail);

        String bodyTemplate = """
                Olá {EMPLOYEE_NAME},
                Temos o prazer de informar que sua conta foi criada com sucesso.
                              
                Aqui estão seus detalhes:
                - ID: {ID}
                - Username: {USERNAME}
                - Senha: {PASSWORD}
                - Telefone: {PHONE}
                - Data de Nascimento: {BIRTH_DATE}
                - CPF: {CPF}
                - Função: {ROLE}
                - Endereço: {STREET}, {NUMBER} - {NEIGHBORHOOD} - {CITY}/{STATE} - CEP: {ZIPCODE} - {COMPLEMENT}
                
                Bem vindo(a) ao Moto Manager!
                
                Atenciosamente,
                Moto Manager Team
                """;

        String body = bodyTemplate
                .replace("{ID}", defaultIfNull(request.id()))
                .replace("{EMPLOYEE_NAME}", defaultIfNull(request.employeeName()))
                .replace("{USERNAME}", defaultIfNull(request.username()))
                .replace("{PASSWORD}", defaultIfNull(request.password()))
                .replace("{PHONE}", defaultIfNull(request.phone()))
                .replace("{BIRTH_DATE}", formatBirthDate(request.birthDate()))
                .replace("{CPF}", defaultIfNull(request.cpf()))
                .replace("{ROLE}", defaultIfNull(request.role()))
                .replace("{STREET}", defaultIfNull(request.street()))
                .replace("{NUMBER}", defaultIfNull(request.number()))
                .replace("{NEIGHBORHOOD}", defaultIfNull(request.neighborhood()))
                .replace("{CITY}", defaultIfNull(request.city()))
                .replace("{STATE}", defaultIfNull(request.state()))
                .replace("{ZIPCODE}", defaultIfNull(request.zipcode()))
                .replace("{COMPLEMENT}", defaultIfNull(request.complement()));

        message.setText(body);

        javaMailSender.send(message);
    }

    private static String formatBirthDate(String birthDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(birthDate).format(formatter);
    }

    private static String defaultIfNull(String value) {
        return value != null ? value : "";
    }
}
