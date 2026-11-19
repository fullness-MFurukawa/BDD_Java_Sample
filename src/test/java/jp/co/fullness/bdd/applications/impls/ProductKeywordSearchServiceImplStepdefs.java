package jp.co.fullness.bdd.applications.impls;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jp.co.fullness.bdd.applications.commons.ExceptionCommonSteps;
import jp.co.fullness.bdd.applications.services.ProductKeywordSearchService;
import jp.co.fullness.bdd.domains.models.products.Product;
import jp.co.fullness.bdd.domains.models.products.ProductName;
import jp.co.fullness.bdd.util.factories.ProductJsonFactory;
import org.junit.jupiter.api.Assertions;

import java.util.List;

/**
 * 演習-11:ProductKeywordSearchServiceImplのシナリオとドライバを作成しテストする
 */
public class ProductKeywordSearchServiceImplStepdefs {
    // 商品検索サービス
    private final ProductKeywordSearchService productKeywordSearchService;
    // JSON形式からProductエンティティを生成するFactory
    private final ProductJsonFactory productJsonFactory;
    // 例外評価共通ステップ
    private final ExceptionCommonSteps exceptionCommonSteps;
    /**
     * コンストラクタ
     * @param productKeywordSearchService 商品検索サービス
     * @param productJsonFactory JSON形式からProductエンティティを生成するFactory
     * @param exceptionCommonSteps 例外評価共通ステップ
     */
    public ProductKeywordSearchServiceImplStepdefs(
            ProductKeywordSearchService productKeywordSearchService,
            ProductJsonFactory productJsonFactory,
            ExceptionCommonSteps exceptionCommonSteps) {
        this.productKeywordSearchService = productKeywordSearchService;
        this.productJsonFactory = productJsonFactory;
        this.exceptionCommonSteps = exceptionCommonSteps;
    }

    private ProductName keyword;
    private List<Product> products;
    private Exception exception;
    @Given("ユーザーが検索キーワードを指定する {string}")
    public void ユーザーが検索キーワードを指定する(String keyword) {
        this.keyword = new ProductName(keyword);
    }

    @When("キーワード検索を実行する")
    public void キーワード検索を実行する() {
        try{
            products = productKeywordSearchService.execute(keyword);
        }catch(Exception e){
            exceptionCommonSteps.setException(e);
        }
    }

    @Then("検索結果には以下の商品が含まれる")
    public void 検索結果には以下の商品が含まれる(String jsonString) throws Exception{
        var expectedProducts = productJsonFactory.convertList(jsonString);
        for (int i = 0; i < expectedProducts.size(); i++){
            Assertions.assertEquals(expectedProducts.get(i).getProductId() ,
                    products.get(i).getProductId());
            Assertions.assertEquals(expectedProducts.get(i).getProductName() ,
                    products.get(i).getProductName());
            Assertions.assertEquals(expectedProducts.get(i).getProductPrice() ,
                    products.get(i).getProductPrice());
            Assertions.assertEquals(
                    expectedProducts.get(i).getCategory().getCategoryId(),
                    products.get(i).getCategory().getCategoryId());
            Assertions.assertEquals(
                    expectedProducts.get(i).getCategory().getCategoryName(),
                    products.get(i).getCategory().getCategoryName());
        }
    }
}
