package co.jp.circleofuniversity.cou.shared.exception.domain;

import co.jp.circleofuniversity.cou.shared.exception.error.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum UserErrorCode implements ErrorCode {
    IS_LATEST(HttpStatus.NO_CONTENT, "U001", "Your message is latest"),
    NO_CHAT_LOG(HttpStatus.NO_CONTENT, "U002", "It is your first chatting!"),
    NOT_VALIDATE_PARAM(HttpStatus.BAD_REQUEST, "U003", "Your order value is too small or big!");

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
