package com.matteusmoreno.mail_sender_moto_manager.customer.service;

import com.matteusmoreno.mail_sender_moto_manager.customer.request.CreateEmailCustomerRequest;
import com.matteusmoreno.mail_sender_moto_manager.customer.request.EnableAndDisableEmailCustomerRequest;
import com.matteusmoreno.mail_sender_moto_manager.customer.request.UpdateEmailCustomerRequest;
import com.matteusmoreno.mail_sender_moto_manager.utils.AppUtils;
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
                .replace("{CREATION_DATE}", defaultIfNull(formatBirthDate(request.createdAt())))
                .replace("{AGE}", defaultIfNull(request.age()))
                .replace("{PHONE}", defaultIfNull(request.phone()))
                .replace("{BIRTH_DATE}", formatBirthDate(request.birthDate()));

        message.setText(body);

        javaMailSender.send(message);
    }

    public void sendCustomerUpdateEmail(UpdateEmailCustomerRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.to());
        message.setSubject("Detalhes da sua conta MotoManager atualizados");
        message.setFrom(hostEmail);

        String bodyTemplate = """
            Olá {CUSTOMER_NAME},
            
            Seus detalhes da conta MotoManager foram atualizados com sucesso!
            
            Detalhes da sua conta:
            - ID: {ID}
            - Nome: {CUSTOMER_NAME}
            - Email: {EMAIL}
            - Data de Nascimento: {BIRTH_DATE}
            - Idade: {AGE}
            - Telefone: {PHONE}
            - Data de Criação: {CREATION_DATE}
            - Data de Atualização: {UPDATE_DATE}
            
            Agradecemos por se juntar a nós e estamos à disposição para qualquer dúvida.
            
            Atenciosamente,
            MotoManager Team
            """;

        String body = bodyTemplate
                .replace("{ID}", defaultIfNull(request.id()))
                .replace("{CUSTOMER_NAME}", defaultIfNull(request.customerName()))
                .replace("{EMAIL}", defaultIfNull(request.email()))
                .replace("{UPDATE_DATE}", defaultIfNull(formatBirthDate(request.updatedAt())))
                .replace("{AGE}", defaultIfNull(request.age()))
                .replace("{PHONE}", defaultIfNull(request.phone()))
                .replace("{BIRTH_DATE}", defaultIfNull(formatBirthDate(request.birthDate())))
                .replace("{CREATION_DATE}", defaultIfNull(formatBirthDate(request.createdAt())));

        message.setText(body);

        javaMailSender.send(message);
    }

    public void sendCustomerDeactivationEmail(EnableAndDisableEmailCustomerRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.to());
        message.setSubject("Notificação de Desativação de Conta");
        message.setFrom(hostEmail);

        String bodyTemplate = """
            Prezado(a) {CUSTOMER_NAME},
            
            Esperamos que esta mensagem o encontre bem. Estamos entrando em contato para informá-lo(a) que sua conta no MotoManager foi desativada.
            
            Detalhes da sua conta:
            - ID: {ID}
            - Nome: {CUSTOMER_NAME}
            - Email: {EMAIL}
            - Data de Nascimento: {BIRTH_DATE}
            - Idade: {AGE}
            - Telefone: {PHONE}
            - Data de Criação: {CREATION_DATE}
            - Data de Atualização: {UPDATE_DATE}
            
            Caso não tenha solicitado essa alteração, por favor entre em contato imediatamente.
            
            Atenciosamente,
            MotoManager Team
            """;

        String body = bodyTemplate
                .replace("{ID}", defaultIfNull(request.id()))
                .replace("{CUSTOMER_NAME}", defaultIfNull(request.customerName()))
                .replace("{EMAIL}", defaultIfNull(request.email()))
                .replace("{AGE}", defaultIfNull(request.age()))
                .replace("{PHONE}", defaultIfNull(request.phone()))
                .replace("{BIRTH_DATE}", defaultIfNull(formatBirthDate(request.birthDate())))
                .replace("{CREATION_DATE}", defaultIfNull(formatBirthDate(request.createdAt())))
                .replace("{UPDATE_DATE}", defaultIfNull(formatBirthDate(request.updatedAt())));


        message.setText(body);

        javaMailSender.send(message);
    }

    public void sendCustomerActivationEmail(EnableAndDisableEmailCustomerRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.to());
        message.setSubject("Notificação de Ativação de Conta");
        message.setFrom(hostEmail);

        String bodyTemplate = """
            Prezado(a) {CUSTOMER_NAME},
            
            É com prazer que informamos que sua conta no Moto Manager foi ativada.    
                    
            Detalhes da sua conta:
            - ID: {ID}
            - Nome: {CUSTOMER_NAME}
            - Email: {EMAIL}
            - Data de Nascimento: {BIRTH_DATE}
            - Idade: {AGE}
            - Telefone: {PHONE}
            - Data de Criação: {CREATION_DATE}
            - Data de Atualização: {UPDATE_DATE}
            
            Caso não tenha solicitado essa alteração, por favor entre em contato imediatamente.
            
            Atenciosamente,
            MotoManager Team
            """;

        String body = bodyTemplate
                .replace("{ID}", defaultIfNull(request.id()))
                .replace("{CUSTOMER_NAME}", defaultIfNull(request.customerName()))
                .replace("{EMAIL}", defaultIfNull(request.email()))
                .replace("{AGE}", defaultIfNull(request.age()))
                .replace("{PHONE}", defaultIfNull(request.phone()))
                .replace("{BIRTH_DATE}", defaultIfNull(formatBirthDate(request.birthDate())))
                .replace("{CREATION_DATE}", defaultIfNull(formatBirthDate(request.createdAt())))
                .replace("{UPDATE_DATE}", defaultIfNull(formatBirthDate(request.updatedAt())));

        message.setText(body);

        javaMailSender.send(message);
    }
}
