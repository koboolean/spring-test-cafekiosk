package com.koboolean.cafekiosk.spring.service.mail;

import com.koboolean.cafekiosk.spring.client.mail.MailSendClient;
import com.koboolean.cafekiosk.spring.domain.history.mail.MailSendHistory;
import com.koboolean.cafekiosk.spring.domain.history.mail.MailSendHistoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class MailServiceTest {

    @Mock
    private MailSendClient mailSendClient;

    @Mock
    private MailSendHistoryRepository mailSendHistoryRepository;

    @InjectMocks
    private MailService mailService;


    @DisplayName("메일 테스트")
    @Test
    void sendMail() {
        // Given
        when(mailSendClient.sendEmail(anyString(), any(String.class), any(String.class), any(String.class)))
                .thenReturn(true);

        /*doReturn(true)
                .when(mailSendClient)
                .sendEmail(anyString(), anyString(), anyString(), anyString());*/

        BDDMockito.given(mailSendClient.sendEmail(anyString(), anyString(), anyString(), anyString())).willReturn(true);

        // When
        boolean result = mailService.sendMail("", "", "", "");

        // Then
        verify(mailSendHistoryRepository, times(1)).save(any(MailSendHistory.class));
        assertTrue(result);
    }
}
