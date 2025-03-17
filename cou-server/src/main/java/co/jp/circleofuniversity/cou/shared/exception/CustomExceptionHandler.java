package co.jp.circleofuniversity.cou.shared.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ProblemDetail> handleCustomException(
            HttpServletRequest request,
            CustomException e
    ) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                e.getHttpStatus(),
                e.getMessage()
        );

        problemDetail.setTitle(e.getMessage());
        problemDetail.setInstance(URI.create(request.getRequestURI()));

        return new ResponseEntity<>(problemDetail, e.getHttpStatus());
    }

    @ExceptionHandler(java.lang.Exception.class)
    public ResponseEntity<ProblemDetail> handleException(
            HttpServletRequest request,
            java.lang.Exception e
    ){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                e.getMessage()
        );
        problemDetail.setTitle("E999");
        problemDetail.setInstance(URI.create(request.getRequestURI()));

        log.error(e.getMessage(), e);

        return new ResponseEntity<>(problemDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


