package jp.co.fullness.bdd.domains.models.commons;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jp.co.fullness.bdd.util.ScenarioContext;
import org.junit.jupiter.api.Assertions;

/**
 * equals()メソッドを評価する共通ステップ
 * @author Fullness,Inc.
 * @since 2025-02-22
 * @version 1.0.1
 */
public class EqualsCommonSteps {
    // シナリオコンテキスト
    private final ScenarioContext scenarioContext;
    /**
     * コンストラクタ
     * @param scenarioContext シナリオコンテキスト
     */
    public EqualsCommonSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }
    /**
     * 評価するインスタンスの設定
     * @param objectA 比較するオブジェクト
     * @param objectB 比較するオブジェクト
     */
    public void SetupObjects(Object objectA, Object objectB){
        scenarioContext.set("objectA",objectA);
        scenarioContext.set("objectB",objectB);
    }
    @When("equalsメソッドを実行する")
    public void equalsメソッドを実行する() {
        var objectA = scenarioContext.get("objectA");
        var objectB = scenarioContext.get("objectB");
        var equalsResult = objectA.equals(objectB);
        scenarioContext.set("result", equalsResult);
    }

    @Then("equalsメソッド実行結果を評価する {string}")
    public void equalsメソッド実行結果を評価する(String expectedResultStr) {
        boolean equalsResult = (boolean) scenarioContext.get("result");
        boolean expectedResult = Boolean.parseBoolean(expectedResultStr);
        Assertions.assertEquals(expectedResult,equalsResult);
    }
}
