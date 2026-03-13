package com.shermatov.emailsender.application.port;

public interface EmailSender {
    void send(EmailMessage emailMessage);
}
