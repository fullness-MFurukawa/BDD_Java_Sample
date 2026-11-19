package jp.co.fullness.bdd.exceptions;
/**
 * データベースアクセスエラーなど内部エラーを表す例外クラス
 * @author Fullness,Inc.
 * @version 1.0
 * @since 2024/10/25
 */
public class InternalException extends RuntimeException{
    public InternalException() {
    }
    public InternalException(String message) {
        super(message);
    }
    public InternalException(String message, Throwable cause) {
        super(message, cause);
    }
    public InternalException(Throwable cause) {
        super(cause);
    }
    public InternalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
