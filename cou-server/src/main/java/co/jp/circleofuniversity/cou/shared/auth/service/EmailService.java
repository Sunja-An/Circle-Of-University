package co.jp.circleofuniversity.cou.shared.auth.service;

import co.jp.circleofuniversity.cou.shared.auth.controller.dto.ResponseTokenDto;
import co.jp.circleofuniversity.cou.shared.auth.entity.ConfirmationToken;
import co.jp.circleofuniversity.cou.shared.auth.repository.MailRepository;
import co.jp.circleofuniversity.cou.shared.auth.utils.AuthStatus;
import co.jp.circleofuniversity.cou.shared.exception.CustomException;
import co.jp.circleofuniversity.cou.shared.exception.domain.AuthErrorCode;
import co.jp.circleofuniversity.cou.user.entity.MemberUser;
import co.jp.circleofuniversity.cou.user.repository.MemberUserRepository;

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
    private final MemberUserRepository memberUserRepository;

    @Autowired
    public EmailService(
            JavaMailSender mailSender,
            MailRepository mailRepository,
            JwtService jwtService,
            MemberUserRepository memberUserRepository
    ) {
        this.mailSender = mailSender;
        this.mailRepository = mailRepository;
        this.jwtService = jwtService;
        this.memberUserRepository = memberUserRepository;
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
        // Null 의 가능성이 있는 것은 Entity 로 받아와서 if 문으로 처리하는 것보다는
        // Optional 로 받아와서, orElseThrow 로 처리하는 것이 좋다.

        // 토큰 탈취 위험이 있으니 한번 생각해보기.
        ConfirmationToken token = mailRepository.findByConfirmationToken(confirmationToken)
                .orElseThrow(() -> CustomException.of(AuthErrorCode.INVALID_TOKEN));

        String email = token.getEmail();

        if(memberUserRepository.findByEmail(email).isEmpty()){
            log.info("[Confirm Email] Email Doesn't Exist");
            MemberUser memberUser = new MemberUser(email, token);
            memberUserRepository.save(memberUser);

            return ResponseEntity.ok().body(AuthStatus.NEED_SIGNUP);
        }

        final String accessToken = jwtService.generateAccessToken(email, token.getRole());
        final String refreshToken = jwtService.generateRefreshToken(email);

        ResponseTokenDto responseToken = new ResponseTokenDto(accessToken, refreshToken);

        return ResponseEntity.ok().body(responseToken);
    }
}
