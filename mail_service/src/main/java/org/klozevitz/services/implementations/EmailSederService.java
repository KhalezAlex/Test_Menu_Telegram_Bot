package org.klozevitz.services.implementations;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.klozevitz.dto.MailParameters;
import org.klozevitz.services.interfaces.EmailSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Log4j
@Service
@RequiredArgsConstructor
public class EmailSederService implements EmailSender {
    @Value("${spring.mail.username}")
    private String emailFrom;
    @Value("${service.activation.uri}")
    private String activationServiceUri;
    private final JavaMailSender javaMailSender;


    @Override
    public void send(MailParameters mailParameters) {
        var subject = "Активация учетной записи компании";
        var messageBody = activationMailBody(mailParameters.getId());
        var emailTo = mailParameters.getEmailTo();

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(emailFrom);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(messageBody);

        javaMailSender.send(mailMessage);
    }

    private String activationMailBody(String id) {
        var messageBody = String.format("Для завершения регистрации компании пройдите по ссылке:\n%s",
                activationServiceUri);
        return messageBody.replace("{id}", id);
    }
}
