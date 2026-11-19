package jp.co.fullness.bdd.domains.models.categories;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jp.co.fullness.bdd.domains.models.commons.EqualsCommonSteps;
import jp.co.fullness.bdd.domains.models.commons.HashCodeCommonSteps;
import jp.co.fullness.bdd.domains.models.commons.ValidateExceptionCommonSteps;
import jp.co.fullness.bdd.exceptions.ValidateException;
import org.junit.jupiter.api.Assertions;

/**
 * 値オブジェクト:商品カテゴリIdの単体テストドライバ
 * @author Fullness,Inc.
 * @since 2025-02-22
 * @version 1.0.1
 */
public class CategoryIdStepdefs {

    // ValidateException評価共通ステップ
    private final ValidateExceptionCommonSteps validateExceptionCommonSteps;
    // equals()メソッドを評価する共通ステップ
    private final EqualsCommonSteps equalsCommonSteps;
    // hashCode()メソッドを評価する共通ステップ
    private final HashCodeCommonSteps hashCodeCommonSteps;
    /**
     * コンストラクタ
     * @param validateExceptionCommonSteps ValidateException評価共通ステップ
     * @param equalsCommonSteps equals()メソッドを評価する共通ステップ
     * @param hashCodeCommonSteps hashCode()メソッドを評価する共通ステップ
     */
    public CategoryIdStepdefs(
            ValidateExceptionCommonSteps validateExceptionCommonSteps,
            EqualsCommonSteps equalsCommonSteps,
            HashCodeCommonSteps hashCodeCommonSteps) {
        this.validateExceptionCommonSteps = validateExceptionCommonSteps;
        this.equalsCommonSteps = equalsCommonSteps;
        this.hashCodeCommonSteps = hashCodeCommonSteps;
    }

    /**
     * インスタンス生成の評価
     */
    private String _id;
    private CategoryId _categoryId;

    @Given("商品カテゴリId {string} を用意する")
    public void 商品カテゴリidを用意する(String id) {
        if (id.equals("null")){
            _id = null;
        }else{
            _id = id;
        }
    }

    @When("商品カテゴリIdを生成する")
    public void 商品カテゴリidを生成する() {
        try
        {
            _categoryId = new CategoryId(_id);
        }
        catch (ValidateException e)
        {
            // ValidateExceptionCommonStepsに例外を設定する
            this.validateExceptionCommonSteps.setException(e);
        }
    }

    @Then("商品カテゴリIdの値は {string} である")
    public void 商品カテゴリidの値はである(String expectedId) {
        // 商品カテゴリIdが等価であることを評価する
        Assertions.assertEquals(expectedId,_categoryId.getValue());
    }

    /**
     * equals()メソッドの評価
     * 共通ステップ:EqualsCommonSteps利用
     */
    @Given("商品カテゴリIdを比較する値を用意する {string} {string}")
    public void 商品カテゴリidを比較する値を用意する(String value1, String value2) {
        // EqualsCommonStepsでメソッド実行と結果を評価する
        equalsCommonSteps.SetupObjects(
                new CategoryId(value1),new CategoryId(value2));
    }
    /**
     * hashCode()メソッドの評価
     * 共通ステップ:HashCodeCommonSteps利用
     */
    @Given("商品カテゴリIdのハッシュ値を生成する値を用意する {string} {string}")
    public void 商品カテゴリidのハッシュ値を生成する値を用意する(String value1, String value2) {
        // HashCodeCommonStepsでメソッド実行と結果を評価する
        hashCodeCommonSteps.SetupObjects(
                new CategoryId(value1),new CategoryId(value2));
    }
}
