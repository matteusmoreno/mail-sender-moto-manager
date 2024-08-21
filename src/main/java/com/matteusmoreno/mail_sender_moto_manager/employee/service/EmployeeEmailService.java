package com.matteusmoreno.mail_sender_moto_manager.employee.service;

import com.matteusmoreno.mail_sender_moto_manager.employee.request.CreateEmailEmployeeRequest;
import com.matteusmoreno.mail_sender_moto_manager.employee.request.EnableAndDisableEmailEmployeeRequest;
import com.matteusmoreno.mail_sender_moto_manager.employee.request.UpdateEmailEmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class EmployeeEmailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmployeeEmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Value("${SPRING_GMAIL_USERNAME}")
    private String hostEmail;

    public void sendEmployeeCreationEmail(CreateEmailEmployeeRequest request) {
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

    public void sendEmployeeUpdateEmail(UpdateEmailEmployeeRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.to());
        message.setSubject("DADOS DA CONTA ATUALIZADOS COM SUCESSO");
        message.setFrom(hostEmail);

        String bodyTemplate = """
            Olá {EMPLOYEE_NAME},
            Seus dados foram atualizados com sucesso.
                          
            Aqui estão seus novos detalhes:
            - ID: {ID}
            - Username: {USERNAME}
            - Telefone: {PHONE}
            - Data de Nascimento: {BIRTH_DATE}
            - Idade : {AGE}
            - CPF: {CPF}
            - Função: {ROLE}
            - Endereço: {STREET}, {NUMBER} - {NEIGHBORHOOD} - {CITY}/{STATE} - CEP: {ZIPCODE} - {COMPLEMENT}
            
            Caso não tenha solicitado essa alteração, por favor entre em contato imediatamente.
            
            Atenciosamente,
            Moto Manager Team
            """;

        String body = bodyTemplate
                .replace("{ID}", defaultIfNull(request.id()))
                .replace("{EMPLOYEE_NAME}", defaultIfNull(request.employeeName()))
                .replace("{USERNAME}", defaultIfNull(request.username()))
                .replace("{PHONE}", defaultIfNull(request.phone()))
                .replace("{BIRTH_DATE}", formatBirthDate(request.birthDate()))
                .replace("{AGE}", request.age())
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

    public void sendEmployeeDeactivationEmail(EnableAndDisableEmailEmployeeRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.to());
        message.setSubject("Notificação de Desativação de Conta");
        message.setFrom(hostEmail);

        String bodyTemplate = """
            Prezado(a) {EMPLOYEE_NAME},
            
            Esperamos que esta mensagem o encontre bem. Estamos entrando em contato para informá-lo(a) que sua conta no Moto Manager foi desativada.
            
            Detalhes da sua conta:
            - Username: {USERNAME}
            - CPF: {CPF}
            - Função: {ROLE}
            
            Se você acredita que houve um erro ou deseja mais informações, entre em contato com o departamento de recursos humanos ou com o suporte técnico.
            
            Agradecemos pela sua contribuição e desejamos sucesso em seus futuros empreendimentos.
            
            Atenciosamente,
            Moto Manager Team
            """;

        String body = bodyTemplate
                .replace("{EMPLOYEE_NAME}", defaultIfNull(request.employeeName()))
                .replace("{USERNAME}", defaultIfNull(request.username()))
                .replace("{CPF}", defaultIfNull(request.cpf()))
                .replace("{ROLE}", defaultIfNull(request.role()));

        message.setText(body);
        javaMailSender.send(message);
    }

    public void sendEmployeeActivationEmail(EnableAndDisableEmailEmployeeRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.to());
        message.setSubject("Notificação de Ativação de Conta");
        message.setFrom(hostEmail);

        String bodyTemplate = """
            Prezado(a) {EMPLOYEE_NAME},
            
            É com prazer que informamos que sua conta no Moto Manager foi ativada.
            
            Detalhes da sua conta:
            - Username: {USERNAME}
            - CPF: {CPF}
            - Função: {ROLE}
            
            Você agora tem acesso total ao sistema e pode retomar suas atividades.
            
            Caso precise de qualquer suporte ou tenha dúvidas, nossa equipe está à disposição.
            
            Bem-vindo(a) de volta!
            
            Atenciosamente,
            Moto Manager Team
            """;

        String body = bodyTemplate
                .replace("{EMPLOYEE_NAME}", defaultIfNull(request.employeeName()))
                .replace("{USERNAME}", defaultIfNull(request.username()))
                .replace("{CPF}", defaultIfNull(request.cpf()))
                .replace("{ROLE}", defaultIfNull(request.role()));

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
