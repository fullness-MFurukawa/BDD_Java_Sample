package jp.co.fullness.bdd.exceptions;
/**
 * 取得目的のエンティティが見つからないことを表す例外クラス
 * @author Fullness,Inc.
 * @version 1.0
 * @since 2024/10/25
 */
public class NotFoundException extends RuntimeException{
    public NotFoundException() {
    }
    public NotFoundException(String message) {
        super(message);
    }
    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public NotFoundException(Throwable cause) {
        super(cause);
    }
    public NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
