package co.jp.circleofuniversity.cou.shared.auth.controller;

import co.jp.circleofuniversity.cou.shared.auth.controller.dto.CreateUserDto;
import co.jp.circleofuniversity.cou.shared.auth.controller.dto.LoginUserDto;
import co.jp.circleofuniversity.cou.shared.auth.service.AuthService;
import co.jp.circleofuniversity.cou.shared.auth.service.EmailService;

import jakarta.mail.MessagingException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthController implements AuthApiSpec{

    private final AuthService authService;
    private final EmailService emailService;

    @Override
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserDto loginUserDto) throws MessagingException {
        return authService.login(loginUserDto);
    }

    @Override
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody CreateUserDto createUserDto) {
        return authService.signup(createUserDto);
    }

    @Override
    @GetMapping(value = "/confirm-email")
    public ResponseEntity<?> checkEmail(@RequestParam("token") String token) {
        return emailService.confirmEmail(token);
    }
}
