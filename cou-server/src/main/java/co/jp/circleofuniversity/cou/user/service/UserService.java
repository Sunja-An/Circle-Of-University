package co.jp.circleofuniversity.cou.user.service;

import co.jp.circleofuniversity.cou.shared.auth.controller.dto.CreateUserDto;
import co.jp.circleofuniversity.cou.shared.auth.controller.dto.LoginUserDto;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;

public interface UserService {
    public boolean isUserExist(String email);
    public boolean isUsernameExist(String username);
    public ResponseEntity<?> confirmEmail(LoginUserDto loginUserDto);
    public ResponseEntity<?> saveUser(CreateUserDto createUserDto);
    public ResponseEntity<?> sendEmail(String email) throws MessagingException;
    public ResponseEntity<?> confirmEmail(String confirmationToken);
}
