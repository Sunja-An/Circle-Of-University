package co.jp.circleofuniversity.cou.shared.exception.domain;

import co.jp.circleofuniversity.cou.shared.exception.error.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum FileErrorCode implements ErrorCode {
    NOT_EMPTY(HttpStatus.NOT_ACCEPTABLE, "F001", "ファイルアプロードーする空間がないです。"),
    UPLOAD_FAILURE(HttpStatus.INTERNAL_SERVER_ERROR, "F002", "ファイルアプロードーに失敗しました。"),
    INIT_FAILURE(HttpStatus.INTERNAL_SERVER_ERROR, "F003", "ファイル初期化を失敗しました。"),
    BOUNDARY_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "F004", "ファイルdirectoryの外にデーターを置けないです。"),
    CANNOT_LOAD(HttpStatus.INTERNAL_SERVER_ERROR, "F005", "ファイルを引き出すのができませんでした。"),
    EMPTY_UPLOAD(HttpStatus.BAD_REQUEST, "F006", "アプロードーするファイルがないです"),
    EMPTY_LOAD(HttpStatus.NOT_FOUND, "F007", "Pathに合ってるファイルがないです。");

    private HttpStatus httpStatus;
    private String messsage;
    private String statusCode;

    @Override
    public String message() {
        return this.messsage;
    }

    @Override
    public String statusCode() {
        return this.statusCode;
    }

    @Override
    public HttpStatus httpStatus() {
        return this.httpStatus;
    }
}
