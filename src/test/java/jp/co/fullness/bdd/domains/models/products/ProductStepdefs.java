package jp.co.fullness.bdd.domains.models.products;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jp.co.fullness.bdd.domains.models.categories.Category;
import jp.co.fullness.bdd.domains.models.commons.EqualsCommonSteps;
import jp.co.fullness.bdd.domains.models.commons.HashCodeCommonSteps;
import jp.co.fullness.bdd.domains.models.commons.ValidateExceptionCommonSteps;
import jp.co.fullness.bdd.exceptions.ValidateException;
import jp.co.fullness.bdd.util.ScenarioContext;
import org.junit.jupiter.api.Assertions;

import java.util.Map;
import java.util.stream.Stream;

/**
 * 演習-03:エンティティ:商品(Product)の単体テスト
 */
public class ProductStepdefs {
    private final ValidateExceptionCommonSteps validateExceptionCommonSteps;
    // equals()メソッドを評価する共通ステップ
    private final EqualsCommonSteps equalsCommonSteps;
    // hashCode()メソッドを評価する共通ステップ
    private final HashCodeCommonSteps hashCodeCommonSteps;
    // シナリオコンテキスト
    private final ScenarioContext scenarioContext;
    /**
     * コンストラクタ
     * @param validateExceptionCommonSteps ValidateException評価共通ステップ
     * @param equalsCommonSteps equals()メソッドを評価する共通ステップ
     * @param hashCodeCommonSteps hashCode()メソッドを評価する共通ステップ
     * @param scenarioContext シナリオコンテキスト
     */
    public ProductStepdefs(
            ValidateExceptionCommonSteps validateExceptionCommonSteps,
            EqualsCommonSteps equalsCommonSteps,
            HashCodeCommonSteps hashCodeCommonSteps,
            ScenarioContext scenarioContext) {
        this.validateExceptionCommonSteps = validateExceptionCommonSteps;
        this.equalsCommonSteps = equalsCommonSteps;
        this.hashCodeCommonSteps = hashCodeCommonSteps;
        this.scenarioContext = scenarioContext;
    }

    private String productId;
    private String productName;
    private Integer productPrice;
    private String categoryId;
    private String categoryName;
    private Product product;

    @Given("新しい商品名、単価を用意する {string} {string}")
    public void 新しい商品名単価を用意する(String name, String price) {
        if (! name.equals("null")){
            productName = name;
        }
        if (! price.equals("null")){
            productPrice = Integer.parseInt(price);
        }
    }

    @When("新しい商品を生成する")
    public void 新しい商品を生成する() {
        try{
            product = new Product(productName,productPrice);
        }catch(ValidateException e){
            validateExceptionCommonSteps.setException(e);
        }
    }

    @Then("新しい商品Idを持った商品が生成されていることを評価する {string} {string}")
    public void 新しい商品idを持った商品が生成されていることを評価する(
            String expectedName, String expectedPrice) {
        Assertions.assertNotNull(product.getProductId());
        Assertions.assertEquals(expectedName,
                product.getProductName().getValue());
        Assertions.assertEquals(Integer.parseInt(expectedPrice) ,
                product.getProductPrice().getValue());
    }

    @Given("既存の商品Id、商品名、単価を用意する {string} {string} {string}")
    public void 既存の商品id商品名単価を用意する(
            String id, String name, String price) {
        if (! id.equals("null")){
            productId = id;
        }
        if (! name.equals("null")){
            productName = name;
        }
        if (! price.equals("null")){
            productPrice = Integer.parseInt(price);
        }
    }

    @When("既存の商品を生成する")
    public void 既存の商品を生成する() {
        try{
            product = new Product(productId,productName,productPrice);
        }catch(ValidateException e){
            validateExceptionCommonSteps.setException(e);
        }
    }

    @Then("商品Id、商品名、単価は等価であることを評価する {string} {string} {string}")
    public void 商品id商品名単価は等価であることを評価する(
            String id, String name, String price) {
        Assertions.assertEquals(id , product.getProductId().getValue());
        Assertions.assertEquals(name ,
                product.getProductName().getValue());
        Assertions.assertEquals(Integer.parseInt(price) ,
                product.getProductPrice().getValue());
    }

    /**
     * changeName()、changePrice()メソッドの検証
     */
    @Given("新しい商品名、単価、カテゴリを用意する")
    public void 新しい商品名単価カテゴリを用意する(DataTable dataTable) {
        // DataTableをMap<String, String>のリストに変換し、Streamに変換する
        Stream<Map<String, String>> dataStream =
                dataTable.asMaps(String.class, String.class).stream();
        // Streamの最初の要素を取得し、その要素が存在する場合に値を取り出す
        dataStream.findFirst().ifPresent(dataMap -> {
            // nameフィールドに値を取得する
            if (! dataMap.get("name").equals("null")){
                productName = dataMap.get("name");
            }
            // priceフィールドに値を取得する
            if (! dataMap.get("price").equals("null")){
                productPrice = Integer.parseInt(dataMap.get("price"));
            }
            // category_idフィールドに値を取得する
            if (! dataMap.get("category_id").equals("null")){
                categoryId = dataMap.get("category_id");
            }
            // category_nameフィールドに値を取得する
            if (! dataMap.get("category_name").equals("null")){
                categoryName = dataMap.get("category_name");
            }
        });
        var category = new Category(categoryId,categoryName);
        product = new Product(productName,productPrice);
        product.changeCategory(category);
    }

    @When("商品名を変更する {string}")
    public void 商品名を変更する(String newName) {
        try{
            if (newName.equals("null")){
                newName = null;
            }
            product.changeProductName(newName);
        }catch(ValidateException e){
            validateExceptionCommonSteps.setException(e);
        }
    }

    @Then("商品名が変更されていることを検証する {string}")
    public void 商品名が変更されていることを検証する(String expectedName) {
        Assertions.assertEquals(expectedName ,
                product.getProductName().getValue());
    }

    @When("商品単価を変更する {string}")
    public void 商品単価を変更する(String newPrice) {
        try {
            if (newPrice.equals("null")){
                product.changeProductPrice(null);
            }else{
                product.changeProductPrice(Integer.parseInt(newPrice));
            }
        }catch(ValidateException e){
            validateExceptionCommonSteps.setException(e);
        }
    }

    @Then("商品単価が変更されていることを検証する {string}")
    public void 商品単価が変更されていることを検証する(String expectedPrice) {
        Assertions.assertEquals(Integer.parseInt(expectedPrice) ,
                product.getProductPrice().getValue());
    }

    /**
     * equals()、hashCode()メソッドの検証
     */
    @Given("比較対象の商品を用意する {string} {string} {string} {string} {string} {string}")
    public void 比較対象の商品を用意する(
            String id1, String name1, String price1, String id2, String name2, String price2) {
        var objectA = new Product(id1,name1,Integer.parseInt(price1));
        var objectB = new Product(id2,name2,Integer.parseInt(price2));
        scenarioContext.set("objectA",objectA);
        scenarioContext.set("objectB",objectB);
    }

    @Given("ハッシュ値を生成する商品を用意する {string} {string} {string} {string} {string} {string}")
    public void ハッシュ値を生成する商品を用意する(
            String id1, String name1, String price1, String id2, String name2, String price2) {
        var objectA = new Product(id1,name1,Integer.parseInt(price1));
        var objectB = new Product(id2,name2,Integer.parseInt(price2));
        scenarioContext.set("objectA",objectA);
        scenarioContext.set("objectB",objectB);
    }
}
