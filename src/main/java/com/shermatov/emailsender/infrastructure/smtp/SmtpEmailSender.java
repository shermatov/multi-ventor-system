package com.shermatov.emailsender.infrastructure.smtp;


import com.shermatov.emailsender.application.port.EmailMessage;
import com.shermatov.emailsender.application.port.EmailSender;
import com.shermatov.emailsender.domain.exception.EmailSendFailedException;
import com.shermatov.emailsender.infrastructure.config.EmailProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SmtpEmailSender implements EmailSender {

    private final JavaMailSender javaMailSender;
    private final EmailProperties properties;

    @Override
    public void send(EmailMessage message) {

        if (!properties.enabled()) {
            log.info("SMTP sending is disabled");
            return;
        }

        var mime = javaMailSender.createMimeMessage();

        try {
            var helper = new MimeMessageHelper(mime, "UTF-8");

            helper.setFrom(properties.from());
            helper.setTo(message.to());
            helper.setSubject(message.subject());
            helper.setText(message.body(), message.html());

            javaMailSender.send(mime);

        } catch (Exception e) {
            log.error("Failed to send email to {}", message.to(), e);
            throw new EmailSendFailedException("Failed to send email", e);
        }
    }
}