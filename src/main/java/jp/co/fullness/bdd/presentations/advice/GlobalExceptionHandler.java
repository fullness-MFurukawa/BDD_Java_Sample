package jp.co.fullness.bdd.presentations.advice;

import jp.co.fullness.bdd.exceptions.InternalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
/**
 * InternalException例外処理アドバイス
 * 提供
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    // Loggerのインスタンスを作成
    private static final Logger logger =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(InternalException.class) // InternalExceptionをキャッチ
    public ModelAndView handleNullPointerException(Exception ex, Model model) {
        // StackTraceをログに出力
        logger.error("内部例外が発生しました。", ex);
        // error/error.htmlに遷移
        return new ModelAndView("error/error");
    }
}
