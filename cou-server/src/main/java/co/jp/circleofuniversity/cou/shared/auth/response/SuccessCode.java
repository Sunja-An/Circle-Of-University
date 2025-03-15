package co.jp.circleofuniversity.cou.shared.auth.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessCode {
    OK(HttpStatus.OK.value(), "OK"),
    CREATED(HttpStatus.CREATED.value(), "保存に成功しました。"),
    NO_CONTENT(HttpStatus.NO_CONTENT.value(), "データの削除が成功しました。");

    private final int code;
    private final String message;
}
