package com.matteusmoreno.mail_sender_moto_manager.customer.service;

import com.matteusmoreno.mail_sender_moto_manager.customer.request.CreateEmailCustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static com.matteusmoreno.mail_sender_moto_manager.utils.AppUtils.defaultIfNull;
import static com.matteusmoreno.mail_sender_moto_manager.utils.AppUtils.formatBirthDate;

@Service
public class CustomerEmailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public CustomerEmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Value("${SPRING_GMAIL_USERNAME}")
    private String hostEmail;

    public void sendCustomerCreationEmail(CreateEmailCustomerRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.to());
        message.setSubject("Bem-vindo ao MotoManager!");
        message.setFrom(hostEmail);

        String bodyTemplate = """
            Olá {CUSTOMER_NAME},
            
            Sua conta foi criada com sucesso no MotoManager!
            
            Detalhes da sua conta:
            - ID: {ID}
            - Nome: {CUSTOMER_NAME}
            - Data de Nascimento: {BIRTH_DATE}
            - Idade: {AGE}
            - Telefone: {PHONE}
            - Data de Criação: {CREATION_DATE}
            
            Agradecemos por se juntar a nós e estamos à disposição para qualquer dúvida.
            
            Atenciosamente,
            MotoManager Team
            """;

        String body = bodyTemplate
                .replace("{ID}", defaultIfNull(request.id()))
                .replace("{CUSTOMER_NAME}", defaultIfNull(request.customerName()))
                .replace("{CREATION_DATE}", defaultIfNull(request.createdAt()))
                .replace("{AGE}", defaultIfNull(request.age()))
                .replace("{PHONE}", defaultIfNull(request.phone()))
                .replace("{BIRTH_DATE}", formatBirthDate(request.birthDate()));

        message.setText(body);

        javaMailSender.send(message);
    }
}
