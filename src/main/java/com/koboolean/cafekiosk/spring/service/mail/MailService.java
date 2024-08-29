package com.koboolean.cafekiosk.spring.service.mail;

import com.koboolean.cafekiosk.spring.client.mail.MailSendClient;
import com.koboolean.cafekiosk.spring.domain.history.mail.MailSendHistory;
import com.koboolean.cafekiosk.spring.domain.history.mail.MailSendHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MailService {

    private final MailSendClient mailSendClient;

    private final MailSendHistoryRepository mailSendHistoryRepository;

    public boolean sendMail(String fromEmail, String toEmail, String subject, String content) {
        boolean result = mailSendClient.sendEmail(fromEmail, toEmail, subject, content);
        if (result) {
            mailSendHistoryRepository.save(MailSendHistory.builder()
                    .fromEmail(fromEmail)
                    .email(toEmail)
                    .title(subject)
                    .content(content)
                    .build()
            );

            mailSendClient.a();
            mailSendClient.b();
            mailSendClient.c();

            return true;
        }

        return false;
    }
}
