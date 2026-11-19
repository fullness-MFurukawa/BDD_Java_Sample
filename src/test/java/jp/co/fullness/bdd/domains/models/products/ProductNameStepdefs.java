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

/**
 * ProductNameのテストドライバクラス
 */
public class ProductNameStepdefs {

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
    public ProductNameStepdefs(
            ValidateExceptionCommonSteps validateExceptionCommonSteps,
            EqualsCommonSteps equalsCommonSteps,
            HashCodeCommonSteps hashCodeCommonSteps,
            ScenarioContext scenarioContext) {
        this.validateExceptionCommonSteps = validateExceptionCommonSteps;
        this.equalsCommonSteps = equalsCommonSteps;
        this.hashCodeCommonSteps = hashCodeCommonSteps;
        this.scenarioContext = scenarioContext;
    }

    private String name;
    private ProductName productName;
    @Given("商品名を用意する {string}")
    public void 商品名を用意する(String name) {
        if (! name.equals("null")){
            this.name = name;
        }
    }
    @When("ProductNameを生成する")
    public void productnameを生成する() {
        try{
            productName = new ProductName(name);
        }catch(ValidateException e){
            validateExceptionCommonSteps.setException(e);
        }
    }
    @Then("ProductNameの値は {string} である")
    public void productnameの値はである(String expectedName) {
        Assertions.assertEquals(expectedName,productName.getValue());
    }

    @Given("商品名を比較する値を用意する {string} {string}")
    public void 商品名を比較する値を用意する(String name1, String name2) {
        var objectA = new ProductName(name1);
        var objectB = new ProductName(name2);
        scenarioContext.set("objectA",objectA);
        scenarioContext.set("objectB",objectB);
    }

    @Given("商品名のハッシュ値を生成する値を用意する {string} {string}")
    public void 商品名のハッシュ値を生成する値を用意する(String name1, String name2) {
        var objectA = new ProductName(name1);
        var objectB = new ProductName(name2);
        scenarioContext.set("objectA",objectA);
        scenarioContext.set("objectB",objectB);
    }
}

