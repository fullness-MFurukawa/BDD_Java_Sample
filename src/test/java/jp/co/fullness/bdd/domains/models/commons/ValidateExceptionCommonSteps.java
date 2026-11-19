package jp.co.fullness.bdd.domains.models.commons;

import io.cucumber.java.en.Then;
import jp.co.fullness.bdd.exceptions.ValidateException;
import org.junit.jupiter.api.Assertions;

/**
 * ValidateException評価用共通ステップ
 * @author Fullness,Inc.
 * @since 2025-02-22
 * @version 1.0.1
 */
public class ValidateExceptionCommonSteps {
    // テストドライバから渡された例外を保持する
    private ValidateException exception;

    /**
     * テストドライバから例外を受け取る
     * @param exception
     */
    public void setException(ValidateException exception) {
        this.exception = exception;
    }

    /**
     * ValidateExceptionを評価する
     * @param expectedMessage
     */
    @Then("ValidateExceptionがスローされる {string}")
    public void validateexceptionがスローされる(String expectedMessage) {
        Assertions.assertNotNull(exception);
        Assertions.assertInstanceOf(ValidateException.class , exception);
        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }
}
