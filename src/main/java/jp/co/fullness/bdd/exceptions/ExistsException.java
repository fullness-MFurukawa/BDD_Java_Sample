package jp.co.fullness.bdd.exceptions;
/**
 * 同一エンティティ(商品)が存在することを表す例外クラス
 * @author Fullness,Inc.
 * @version 1.0
 * @since 2024/10/25
 */
public class ExistsException extends RuntimeException{
    public ExistsException() {
    }
    public ExistsException(String message) {
        super(message);
    }
    public ExistsException(String message, Throwable cause) {
        super(message, cause);
    }
    public ExistsException(Throwable cause) {
        super(cause);
    }
    public ExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
