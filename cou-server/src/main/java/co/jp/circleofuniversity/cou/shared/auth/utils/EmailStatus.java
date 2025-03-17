package co.jp.circleofuniversity.cou.shared.auth.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum EmailStatus {
    SEND_SUCCESS("ES001", "メール送信成功しました。", HttpStatus.OK),
    CONFIRM_TOKEN("ES002", "トークン印象が成功しました。", HttpStatus.OK);

    private final String code;
    private final String message;
    private final HttpStatus status;
}
