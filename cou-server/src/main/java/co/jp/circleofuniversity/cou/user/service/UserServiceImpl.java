package co.jp.circleofuniversity.cou.user.service;

import co.jp.circleofuniversity.cou.shared.auth.controller.dto.CreateUserDto;
import co.jp.circleofuniversity.cou.shared.auth.controller.dto.LoginUserDto;
import co.jp.circleofuniversity.cou.shared.auth.repository.MailRepository;
import co.jp.circleofuniversity.cou.shared.auth.service.EmailService;
import co.jp.circleofuniversity.cou.user.repository.UserRepository;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;

    @Override
    @Transactional
    public boolean isUserExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    @Transactional
    public boolean isUsernameExist(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    @Transactional
    public ResponseEntity<?> confirmEmail(LoginUserDto loginUserDto) {

        return null;
    }

    @Override
    @Transactional
    public ResponseEntity<?> saveUser(CreateUserDto createUserDto) {
        if(userRepository.existsByEmail(createUserDto.email())){
            return ResponseEntity.badRequest().body("Error: Email already exists");
        }

        return ResponseEntity.ok("Verify email by the email link");
    }

    @Override
    @Transactional
    public ResponseEntity<?> sendEmail(String email) throws MessagingException {
        if(userRepository.existsByEmail(email)){
            return ResponseEntity.badRequest().body("[Error]: Email already exists");
        }

        emailService.sendEmail(email);

        return ResponseEntity.ok("[Success]: Email verified");
    }

    @Override
    public ResponseEntity<?> confirmEmail(String confirmationToken) {
        return emailService.confirmEmail(confirmationToken);
    }
}
