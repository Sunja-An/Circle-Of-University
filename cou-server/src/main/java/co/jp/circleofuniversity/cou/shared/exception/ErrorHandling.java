package co.jp.circleofuniversity.cou.shared.exception;

import co.jp.circleofuniversity.cou.shared.exception.error.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorHandling extends RuntimeException{
    private final String errorMessage;
    private final String errorCode;
    private final HttpStatus httpStatus;

    private ErrorHandling(ErrorCode errorCode){
        super(errorCode.toString());
        this.httpStatus = errorCode.httpStatus();
        this.errorCode = errorCode.statusCode();
        this.errorMessage = errorCode.message();
    }

    public static ErrorHandling of(ErrorCode errorCode) {
        return new ErrorHandling(errorCode);
    }
}
