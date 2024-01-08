package com.fpt.duantn.services;

import com.fpt.duantn.ui.model.response.MailInfo;
import jakarta.mail.MessagingException;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Configuration
public interface SendMailService {
    void run();

    void queue(String to, String subject, String body);

    void queue(MailInfo mail);

    void send(MailInfo mail) throws MessagingException, IOException;
}
