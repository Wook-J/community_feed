package org.fastcampus.auth.repository;

import org.fastcampus.auth.application.interfaces.EmailSendRepository;
import org.fastcampus.auth.domain.Email;
import org.springframework.stereotype.Repository;

@Repository
public class EmailSendRepositoryImpl implements EmailSendRepository {

    @Override
    public void sendEmail(Email email, String token) {
        // todo : 원래는 구글SMTP를 이용한 send를 보내야함
    }
}
