package co.jp.circleofuniversity.cou.shared.auth.service;

import co.jp.circleofuniversity.cou.shared.auth.controller.dto.ResponseTokenDto;
import co.jp.circleofuniversity.cou.shared.auth.entity.ConfirmationToken;
import co.jp.circleofuniversity.cou.shared.auth.repository.MailRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
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
    private final JwtService jwtService;

    @Autowired
    public EmailService(JavaMailSender mailSender, MailRepository mailRepository, JwtService jwtService) {
        this.mailSender = mailSender;
        this.mailRepository = mailRepository;
        this.jwtService = jwtService;
    }

    @Async
    public void sendEmail(String email) throws MessagingException {
        ConfirmationToken token = new ConfirmationToken(email);

        MimeMessagePreparator preparation = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");

                // Send to Email
                message.setTo(email);

                // Email Title
                message.setSubject("COU Authentication");

                // Email Content
                message.setText("Click Here to Registeration on COU‼ http://localhost:3000/auth/verify?token="+token.getConfirmationToken());


                ClassPathResource classPathResource = new ClassPathResource("/static/image/Mime_Message_Icon.png");
                message.addAttachment("COU-introduction.png",classPathResource);
            }
        };

        // Save Token
        mailRepository.save(token);

        // Mail Sender
        this.mailSender.send(preparation);
    }

    // Email Token Publish
    public ResponseEntity<?> confirmEmail(String confirmationToken) {
        ConfirmationToken token = mailRepository.findByConfirmationToken(confirmationToken);

        if(token == null){
            return ResponseEntity.badRequest().body("[Error]: Couldn't verify Email");
        }

        final String accessToken = jwtService.generateAccessToken(token.getEmail());
        final String refreshToken = jwtService.generateRefreshToken(token.getEmail());

        return ResponseEntity.ok().body(new ResponseTokenDto(accessToken, refreshToken));
    }
}
