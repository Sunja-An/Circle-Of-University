package co.jp.circleofuniversity.cou.shared.exception.error;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    String message();
    String statusCode();
    HttpStatus httpStatus();
}
