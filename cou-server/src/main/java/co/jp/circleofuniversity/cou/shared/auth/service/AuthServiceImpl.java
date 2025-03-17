package co.jp.circleofuniversity.cou.shared.auth.service;

import co.jp.circleofuniversity.cou.shared.auth.controller.dto.CreateUserDto;
import co.jp.circleofuniversity.cou.shared.auth.controller.dto.LoginUserDto;
import co.jp.circleofuniversity.cou.shared.auth.utils.AuthStatus;
import co.jp.circleofuniversity.cou.shared.auth.utils.EmailStatus;
import co.jp.circleofuniversity.cou.shared.exception.CustomException;
import co.jp.circleofuniversity.cou.shared.exception.domain.AuthErrorCode;
import co.jp.circleofuniversity.cou.user.entity.MemberUser;
import co.jp.circleofuniversity.cou.user.repository.MemberUserRepository;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final EmailService emailService;
    private final MemberUserRepository memberUserRepository;

    @Override
    public ResponseEntity<?> login(LoginUserDto loginUserDto) throws MessagingException {
        emailService.sendEmail(loginUserDto.email());
        return ResponseEntity.ok(EmailStatus.SEND_SUCCESS.getMessage());
    }

    @Override
    public ResponseEntity<?> signup(CreateUserDto createUserDto) {
        final Optional<MemberUser> member = memberUserRepository.findByEmail(createUserDto.email());

        if(member.isPresent()){
            throw CustomException.of(AuthErrorCode.SAME_USER);
        }

//        memberUserRepository.save(createUserDto);

        return ResponseEntity.ok(AuthStatus.SIGNUP_SUCCESS.getMessage());
    }
}
