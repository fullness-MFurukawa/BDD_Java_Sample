package jp.co.fullness.bdd.domains.models.products;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jp.co.fullness.bdd.domains.models.commons.EqualsCommonSteps;
import jp.co.fullness.bdd.domains.models.commons.HashCodeCommonSteps;
import jp.co.fullness.bdd.domains.models.commons.ValidateExceptionCommonSteps;
import jp.co.fullness.bdd.exceptions.ValidateException;
import org.junit.jupiter.api.Assertions;

/**
 * 演習-01:値オブジェクト:ProductIdの単体テスト(インスタンス生成)
 */
public class ProductIdStepdefs {
    /**
     * 演習-02 共通ステップを利用して、すべてのテストを実装して検証する
     */
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
    public ProductIdStepdefs (
            ValidateExceptionCommonSteps validateExceptionCommonSteps,
            EqualsCommonSteps equalsCommonSteps,
            HashCodeCommonSteps hashCodeCommonSteps) {
        this.validateExceptionCommonSteps = validateExceptionCommonSteps;
        this.equalsCommonSteps = equalsCommonSteps;
        this.hashCodeCommonSteps = hashCodeCommonSteps;
    }


    private String _id;
    private ProductId _productId;
    @Given("商品Idを用意する {string}")
    public void 商品idを用意する(String id) {
        if (! id.equals("null")){
            _id = id;
        }
    }

    @When("商品Idを生成する")
    public void 商品idを生成する() {
        try{
            _productId = new ProductId(_id);
        }catch(ValidateException e){
            validateExceptionCommonSteps.setException(e);
        }
    }

    @Then("商品Idの値は {string} である")
    public void 商品idの値はである(String expectedId) {
        Assertions.assertEquals(expectedId,_productId.getValue());
    }

    @Given("商品Idを比較する値を用意する {string} {string}")
    public void 商品idを比較する値を用意する(String value1, String value2) {
        equalsCommonSteps.SetupObjects(
                new ProductId(value1) , new ProductId(value2));
    }

    @Given("商品Idのハッシュ値を生成する値を用意する {string} {string}")
    public void 商品idのハッシュ値を生成する値を用意する(String value1, String value2) {
        hashCodeCommonSteps.SetupObjects(
                new ProductId(value1) , new ProductId(value2));
    }
}
