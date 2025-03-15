package co.jp.circleofuniversity.cou.shared.exception.domain;

import co.jp.circleofuniversity.cou.shared.exception.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum AuthErrorCode implements ErrorCode {
    NO_TOKEN(HttpStatus.UNAUTHORIZED, "A001", "トークンが見付かれないです。"),
    NO_USER(HttpStatus.NOT_FOUND, "A002", "ユーザーを見つけれないです。"),
    INVALID_TOKEN(HttpStatus.BAD_REQUEST, "A003", "正しくないトークンです。"),
    EXPIRED_TOKEN(HttpStatus.BAD_REQUEST, "A004", "期間切れトークンです。");

    private HttpStatus httpStatus;
    private String code;
    private String message;

    @Override
    public String message() {
        return this.message;
    }

    @Override
    public String statusCode() {
        return this.code;
    }

    @Override
    public HttpStatus httpStatus() {
        return this.httpStatus;
    }
}
