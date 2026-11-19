package jp.co.fullness.bdd.applications.commons;

import io.cucumber.java.en.Then;
import jp.co.fullness.bdd.exceptions.ExistsException;
import jp.co.fullness.bdd.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;

/**
 * NotFoundException、ExistsException評価共通ステップクラス
 * @author Fullness,Inc.
 * @since 2025-02-23
 * @version 1.0.1
 */
public class ExceptionCommonSteps {
    // 例外を保持するフィールド
    private Exception exception;

    /**
     * 例外のセッター
     * @param exception 例外クラス
     */
    public void setException(Exception exception) {
        this.exception = exception;
    }

    /**
     * NotFoundExceptionを評価するステップ
     * @param expectedMessage 例外メッセージ
     */
    @Then("ユーザーには存在しないことを表す、適切なエラーが返される {string}")
    public void ユーザーには存在しないことを表す適切なエラーが返される(String expectedMessage) {
        Assertions.assertNotNull(exception);
        Assertions.assertInstanceOf(NotFoundException.class, exception);
        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * ExistsExceptionを評価するステップ
     * @param expectedMessage 例外メッセージ
     */
    @Then("ユーザーには存在することを表す、適切なエラーが返される {string}")
    public void ユーザーには存在することを表す適切なエラーが返される(String expectedMessage) {
        Assertions.assertNotNull(exception);
        Assertions.assertInstanceOf(ExistsException.class, exception);
        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }
}
