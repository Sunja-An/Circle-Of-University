package co.jp.circleofuniversity.cou.shared.exception;

import co.jp.circleofuniversity.cou.shared.exception.error.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException{
    private final String errorMessage;
    private final String errorCode;
    private final HttpStatus httpStatus;

    private CustomException(ErrorCode errorCode){
        super(errorCode.toString());
        this.httpStatus = errorCode.httpStatus();
        this.errorCode = errorCode.statusCode();
        this.errorMessage = errorCode.message();
    }

    public static CustomException of(ErrorCode errorCode) {
        return new CustomException(errorCode);
    }
}
