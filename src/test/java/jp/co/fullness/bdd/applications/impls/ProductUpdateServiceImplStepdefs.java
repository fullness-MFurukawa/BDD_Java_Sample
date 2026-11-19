package jp.co.fullness.bdd.applications.impls;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jp.co.fullness.bdd.applications.commons.ExceptionCommonSteps;
import jp.co.fullness.bdd.applications.services.ProductUpdateService;
import jp.co.fullness.bdd.domains.models.products.Product;
import jp.co.fullness.bdd.domains.models.products.ProductId;
import jp.co.fullness.bdd.util.factories.ProductYamlFactory;
import org.junit.jupiter.api.Assertions;

/**
 * 演習-15:ProductUpdateServiceImplのシナリオとドライバを作成しテストする
 */
public class ProductUpdateServiceImplStepdefs {

    // 商品変更サービス
    private final ProductUpdateService productUpdateService;
    // YAML形式からProductエンティティを生成するFactory
    private final ProductYamlFactory productYamlFactory;
    // NotFoundException、ExistsExceptionを評価する共通ステップ
    private final ExceptionCommonSteps exceptionCommonSteps;

    /**
     * コンストラクタ
     * @param productUpdateService 商品変更サービス
     * @param productYamlFactory YAML形式からProductエンティティを生成するFactory
     * @param exceptionCommonSteps NotFoundException、ExistsExceptionを評価する共通ステップ
     */
    public ProductUpdateServiceImplStepdefs(
            ProductUpdateService productUpdateService,
            ProductYamlFactory productYamlFactory,
            ExceptionCommonSteps exceptionCommonSteps) {
        this.productUpdateService = productUpdateService;
        this.productYamlFactory = productYamlFactory;
        this.exceptionCommonSteps = exceptionCommonSteps;
    }

    private ProductId productId;
    private Product product;
    /**
     * - getProduct(): 指定されたキーワードで商品検索した結果を返す
     */
    @Given("ユーザーが「色鉛筆\\(１２色)」を選択する {string}")
    public void ユーザーが色鉛筆１２色を選択する(String id) {
        productId = new ProductId(id);
    }

    @When("選択された商品のIdを利用して商品を取得する")
    public void 選択された商品のidを利用して商品を取得する() {
        try{
            product = productUpdateService.getProduct(productId);
        }catch(Exception e){
            exceptionCommonSteps.setException(e);
        }
    }

    @Then("取得した商品が「色鉛筆\\(１２色)」であることを評価する")
    public void 取得した商品が色鉛筆１２色であることを評価する(String yamlString) throws Exception {
        var expectedProduct = productYamlFactory.convert(yamlString);
        Assertions.assertEquals(expectedProduct.getProductId(),
                product.getProductId());
        Assertions.assertEquals(expectedProduct.getProductName(),
                product.getProductName());
        Assertions.assertEquals(expectedProduct.getProductPrice(),
                product.getProductPrice());
    }

    @Given("ユーザーが存在しない商品を選択する {string}")
    public void ユーザーが存在しない商品を選択する(String id) {
        productId = new ProductId(id);
    }

    /**
     * - update(): 商品を変更する
     */
    @Given("ユーザーが以下の変更内容を入力する")
    public void ユーザーが以下の変更内容を入力する(String yamlString) throws Exception{
        product = productYamlFactory.convert(yamlString);
    }

    @When("商品を変更する")
    public void 商品を変更する() {
        try{
            productUpdateService.update(product);
        }catch(Exception e){
            exceptionCommonSteps.setException(e);
        }
    }

    @Then("変更後の商品情報を取得し、内容が反映されていることを確認する")
    public void 変更後の商品情報を取得し内容が反映されていることを確認する() {
        var expectedProduct = productUpdateService
                .getProduct(product.getProductId());
        Assertions.assertEquals(expectedProduct.getProductName(),
                product.getProductName());
        Assertions.assertEquals(expectedProduct.getProductPrice(),
                product.getProductPrice());
    }
}
