package co.jp.circleofuniversity.cou.shared.auth.service;

import co.jp.circleofuniversity.cou.shared.auth.entity.ConfirmationToken;
import co.jp.circleofuniversity.cou.shared.auth.repository.MailRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;

import org.springframework.stereotype.Service;

@Slf4j
@Service("emailService")
public class EmailService {
    private final JavaMailSender mailSender;
    private final MailRepository mailRepository;

    @Autowired
    public EmailService(JavaMailSender mailSender, MailRepository mailRepository) {
        this.mailSender = mailSender;
        this.mailRepository = mailRepository;
    }

    @Async
    public void sendEmail(String email) throws MessagingException {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");

                ConfirmationToken token = new ConfirmationToken();

                // Send to Email
                message.setTo(email);

                // Email Title
                message.setSubject("COU Authentication");

                // Email Content
                message.setText("Click Here to Registeration on COU‼ http://localhost:8080/v1/auth/confirm-email?token="+token.getConfirmationToken());

                // Save Token
                mailRepository.save(token);

                ClassPathResource classPathResource = new ClassPathResource("/static/image/Mime_Message_Icon.png");
                message.addAttachment("COU-introduction.png",classPathResource);
            }
        };

        try {
            // Mail Sender
            this.mailSender.send(preparator);
            System.out.println("[INFO]: Send Email");
        } catch (MailException error) {
            throw error;
        }
    }

    public ResponseEntity<?> confirmEmail(String confirmationToken) {
        ConfirmationToken token = mailRepository.findByConfirmationToken(confirmationToken);
        if(token == null){
            return ResponseEntity.badRequest().body("[Error]: Couldn't verify Email");
        }else{

            return ResponseEntity.ok().body("[Info]: Email is verified");
        }
    }
}
