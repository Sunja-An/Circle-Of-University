package co.jp.circleofuniversity.cou.shared.auth.controller;

import co.jp.circleofuniversity.cou.shared.auth.controller.dto.CreateUserDto;
import co.jp.circleofuniversity.cou.shared.auth.controller.dto.LoginUserDto;
import co.jp.circleofuniversity.cou.user.service.UserService;
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

    private final UserService userService;

    @Override
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserDto loginUserDto) throws MessagingException {
        return userService.sendEmail(loginUserDto.email());
    }

    @Override
    @GetMapping("/confirm-email")
    @PostMapping("/confirm-email")
    public ResponseEntity<?> checkEmail(@RequestParam("token") String token) {
        return userService.confirmEmail(token);
    }

    @Override
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody  CreateUserDto createUserDto) {
        return null;
    }
}
