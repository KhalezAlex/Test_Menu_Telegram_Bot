package org.klozevitz.services.implementations;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.klozevitz.model.EmailParameters;
import org.klozevitz.services.interfaces.EmailSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Log4j
@Service
@RequiredArgsConstructor
public class EmailSenderService implements EmailSender {
    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String botEmail;
    @Value("${service.activation.uri}")
    private String activationServiceUri;

    @Override
    public void send(EmailParameters parameters) {
        var subject = "Активация учетной записи компании";
        var recipient = parameters.getEmailTo();
        var text = activationMailBody(parameters.getId());

        var mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(botEmail);
        mailMessage.setTo(recipient);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);

        mailSender.send(mailMessage);
    }

    private String activationMailBody(String id) {
        var message = String.format("Для завершения регистрации компании пройдите по ссылке ниже:\n%s",
                activationServiceUri);
        return message.replace("{id}", id);
    }
}
