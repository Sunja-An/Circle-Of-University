package co.jp.circleofuniversity.cou.shared.auth.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AuthStatus {
    LOGIN_SUCCESS("AS001", "ログイン成功", HttpStatus.OK),
    NEED_SIGNUP("AS002", "会員登録が必要です", HttpStatus.OK),
    SIGNUP_SUCCESS("AS003", "会員登録が成功しました", HttpStatus.CREATED),
    INVALID_EMAIL("AS004", "メールの住所が間違っております", HttpStatus.BAD_REQUEST);

    private final String code;
    private final String message;
    private final HttpStatus status;
}
