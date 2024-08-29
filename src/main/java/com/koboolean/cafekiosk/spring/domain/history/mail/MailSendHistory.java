package com.koboolean.cafekiosk.spring.domain.history.mail;

import com.koboolean.cafekiosk.spring.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MailSendHistory extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fromEmail;
    private String email;
    private String title;
    private String content;


    @Builder
    public MailSendHistory(String fromEmail, String email, String title, String content) {
        this.fromEmail = fromEmail;
        this.email = email;
        this.title = title;
        this.content = content;
    }
}
