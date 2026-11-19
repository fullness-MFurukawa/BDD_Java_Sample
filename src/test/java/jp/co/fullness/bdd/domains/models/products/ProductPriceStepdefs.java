package jp.co.fullness.bdd.domains.models.products;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jp.co.fullness.bdd.domains.models.commons.EqualsCommonSteps;
import jp.co.fullness.bdd.domains.models.commons.HashCodeCommonSteps;
import jp.co.fullness.bdd.domains.models.commons.ValidateExceptionCommonSteps;
import jp.co.fullness.bdd.exceptions.ValidateException;
import jp.co.fullness.bdd.util.ScenarioContext;
import org.junit.jupiter.api.Assertions;

public class ProductPriceStepdefs {
    private final ValidateExceptionCommonSteps validateExceptionCommonSteps;
    private final EqualsCommonSteps equalsCommonSteps;
    private final HashCodeCommonSteps hashCodeCommonSteps;
    private final ScenarioContext scenarioContext;

    /**
     * コンストラクタ
     * @param validateExceptionCommonSteps ValidateException評価共通ステップ
     * @param equalsCommonSteps equals()メソッド評価共通ステップ
     * @param hashCodeCommonSteps hashCode()メソッド評価共通ステップ
     * @param scenarioContext シナリオコンテキスト
     */
    public ProductPriceStepdefs(
            ValidateExceptionCommonSteps validateExceptionCommonSteps,
            EqualsCommonSteps equalsCommonSteps,
            HashCodeCommonSteps hashCodeCommonSteps,
            ScenarioContext scenarioContext) {
        this.validateExceptionCommonSteps = validateExceptionCommonSteps;
        this.equalsCommonSteps = equalsCommonSteps;
        this.hashCodeCommonSteps = hashCodeCommonSteps;
        this.scenarioContext = scenarioContext;
    }

    private Integer price;
    private ProductPrice productPrice;
    @Given("商品単価を用意する {string}")
    public void 商品単価を用意する(String price) {
        if (! price.equals("null")){
            this.price = Integer.parseInt(price);
        }
    }
    @When("ProductPriceを生成する")
    public void productpriceを生成する() {
        try{
            productPrice = new ProductPrice(price);
        }catch(ValidateException e){
            validateExceptionCommonSteps.setException(e);
        }
    }
    @Then("ProductPriceの値は {string} である")
    public void productpriceの値はである(String expectedPrice) {
        Integer price = Integer.parseInt(expectedPrice);
        Assertions.assertEquals(price , productPrice.getValue());
    }

    @Given("商品単価を比較する値を用意する {string} {string}")
    public void 商品単価を比較する値を用意する(String price1, String price2) {
        var objectA = new ProductPrice(Integer.parseInt(price1));
        var objectB = new ProductPrice(Integer.parseInt(price2));
        scenarioContext.set("objectA",objectA);
        scenarioContext.set("objectB",objectB);
    }

    @Given("商品単価のハッシュ値を生成する値を用意する {string} {string}")
    public void 商品単価のハッシュ値を生成する値を用意する(String price1, String price2) {
        var objectA = new ProductPrice(Integer.parseInt(price1));
        var objectB = new ProductPrice(Integer.parseInt(price2));
        scenarioContext.set("objectA",objectA);
        scenarioContext.set("objectB",objectB);
    }
}
