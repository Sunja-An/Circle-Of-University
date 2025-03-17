package co.jp.circleofuniversity.cou.shared.auth.service;

import co.jp.circleofuniversity.cou.shared.auth.controller.dto.CreateUserDto;
import co.jp.circleofuniversity.cou.shared.auth.controller.dto.LoginUserDto;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    public ResponseEntity<?> login(LoginUserDto loginUserDto) throws MessagingException;
    public ResponseEntity<?> signup(CreateUserDto createUserDto);
}
