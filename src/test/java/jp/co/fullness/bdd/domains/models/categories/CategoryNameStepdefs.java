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
 * 値オブジェクト:商品カテゴリ名の単体テストドライバ
 * @author Fullness,Inc.
 * @since 2025-02-22
 * @version 1.0.1
 */
public class CategoryNameStepdefs {

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
    public CategoryNameStepdefs(
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
    private String _name;
    private CategoryName _categoryName;
    @Given("商品カテゴリ名を用意する {string}")
    public void 商品カテゴリ名を用意する(String name) {
        if (name.equals("null")){
            _name = null;
        }else{
            _name = name;
        }
    }

    @When("商品カテゴリ名を生成する")
    public void 商品カテゴリ名を生成する() {
        try{
            _categoryName = new CategoryName(_name);
        }catch(ValidateException e){
            // ValidateExceptionCommonStepsに例外を設定する
            validateExceptionCommonSteps.setException(e);
        }
    }

    @Then("商品カテゴリ名の値は {string} である")
    public void 商品カテゴリ名の値はである(String expectedName) {
        Assertions.assertEquals(expectedName,_categoryName.getValue());
    }

    /**
     * equals()メソッドの評価
     * 共通ステップ:EqualsCommonSteps利用
     */
    @Given("商品カテゴリ名を比較する値を用意する {string} {string}")
    public void 商品カテゴリ名を比較する値を用意する(String name1, String name2) {
        // EqualsCommonStepsでメソッド実行と結果を評価する
        equalsCommonSteps.SetupObjects(
                new CategoryName(name1) , new CategoryName(name2));
    }
    /**
     * hashCode()メソッドの評価
     * 共通ステップ:HashCodeCommonSteps利用
     */
    @Given("商品カテゴリ名のハッシュ値を生成する値を用意する {string} {string}")
    public void 商品カテゴリ名のハッシュ値を生成する値を用意する(String name1, String name2) {
        // HashCodeCommonStepsでメソッド実行と結果を評価する
        hashCodeCommonSteps.SetupObjects(
                new CategoryName(name1) , new CategoryName(name2));
    }
}
