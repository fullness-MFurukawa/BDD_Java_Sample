package jp.co.fullness.bdd.applications.impls;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jp.co.fullness.bdd.applications.commons.ExceptionCommonSteps;
import jp.co.fullness.bdd.applications.services.ProductRegisterService;
import jp.co.fullness.bdd.domains.models.categories.Category;
import jp.co.fullness.bdd.domains.models.categories.CategoryId;
import jp.co.fullness.bdd.domains.models.products.Product;
import jp.co.fullness.bdd.domains.models.products.ProductName;
import jp.co.fullness.bdd.util.factories.CategoryYamlFactory;
import jp.co.fullness.bdd.util.factories.ProductYamlFactory;
import org.junit.jupiter.api.Assertions;
import java.util.List;

/**
 * 商品登録サービスのテストドライバ
 * @author Fullness,Inc.
 * @since 2025-02-23
 * @version 1.0.1
 */
public class ProductRegisterServiceImplStepdefs {
    // 商品登録サービス
    private final ProductRegisterService productRegisterService;
    // YAML形式からCategoryエンティティを生成するFactory
    private final CategoryYamlFactory categoryYamlFactory;
    // YAML形式からProductエンティティを生成するFactory
    private final ProductYamlFactory productYamlFactory;
    // NotFoundException、ExistsExceptionを評価する共通ステップ
    private final ExceptionCommonSteps exceptionCommonSteps;

    /**
     * コンストラクタ
     * @param productRegisterService 商品登録サービス
     * @param categoryYamlFactory YAML形式からCategoryエンティティを生成するFactory
     * @param productYamlFactory YAML形式からProductエンティティを生成するFactory
     * @param exceptionCommonSteps NotFoundException、ExistsExceptionを評価する共通ステップ
     */
    public ProductRegisterServiceImplStepdefs(
            ProductRegisterService productRegisterService,
            CategoryYamlFactory categoryYamlFactory,
            ProductYamlFactory productYamlFactory,
            ExceptionCommonSteps exceptionCommonSteps) {
        this.productRegisterService = productRegisterService;
        this.categoryYamlFactory = categoryYamlFactory;
        this.productYamlFactory = productYamlFactory;
        this.exceptionCommonSteps = exceptionCommonSteps;
    }

    /**
     * - getCategories(): すべての商品カテゴリを返す
     */
    private List<Category> categories,expectedCategories;
    @Given("取得される商品カテゴリを準備する")
    public void 取得される商品カテゴリを準備する(String yamlString) throws Exception{
        expectedCategories = categoryYamlFactory.convertList(yamlString);
    }

    @When("商品カテゴリを取得する")
    public void 商品カテゴリを取得する() {
        categories = productRegisterService.getCategories();
    }

    @Then("すべての商品カテゴリが取得できたことを評価する")
    public void すべての商品カテゴリが取得できたことを評価する() {
        for (int i = 0; i < expectedCategories.size(); i++){
            Assertions.assertEquals(expectedCategories.get(i).getCategoryId(),
                    categories.get(i).getCategoryId());
            Assertions.assertEquals(expectedCategories.get(i).getCategoryName(),
                    categories.get(i).getCategoryName());
        }
    }

    /**
     * - getCategory(): 指定された商品カテゴリIdの商品カテゴリを返す
     */
    private CategoryId categoryId;
    private Category category;
    @Given("ユーザーが「雑貨」の商品カテゴリIdを選択する {string}")
    public void ユーザーが雑貨の商品カテゴリIdを選択する(String id) {
        categoryId = new CategoryId(id);
    }

    @When("選択された商品カテゴリIdで商品カテゴリを取得する")
    public void 選択された商品カテゴリidで商品カテゴリを取得する() {
        try{
            category = productRegisterService.getCategory(categoryId);
        }catch(Exception e){
            exceptionCommonSteps.setException(e);
        }
    }

    @Then("商品カテゴリが「雑貨」であることを評価する")
    public void 商品カテゴリが雑貨であることを評価する(String yamlString) throws Exception {
        var expectedCategory = categoryYamlFactory.convert(yamlString);
        Assertions.assertEquals(expectedCategory.getCategoryId(),
                category.getCategoryId());
        Assertions.assertEquals(expectedCategory.getCategoryName(),
                category.getCategoryName());
    }

    @Given("ユーザーが存在しない商品カテゴリIdを選択する {string}")
    public void ユーザーが存在しない商品カテゴリidを選択する(String id) {
        categoryId = new CategoryId(id);
    }

    /**
     * exists(): 商品の有無を調べる
     */
    private ProductName productName;
    private Exception exception;
    @Given("ユーザーが入力した商品名 {string}")
    public void ユーザーが入力した商品名(String name) {
        productName = new ProductName(name);
    }

    @When("商品の存在を確認する")
    public void 商品の存在を確認する() {
        try{
            productRegisterService.exists(productName);
        }
        catch(Exception exception){
            exceptionCommonSteps.setException(exception);
        }
    }

    @Then("商品は未登録である")
    public void 商品は未登録である() {
        Assertions.assertNull(exception);
    }


    /**
     * - register(): 新商品を登録する
     */
    private Product product;
    @Given("ユーザーが以下の新商品を登録しようとしている")
    public void ユーザーが以下の新商品を登録しようとしている(String yamlString) throws Exception{
        product = productYamlFactory.convert(yamlString);
    }

    @When("新商品を登録する")
    public void 新商品を登録する() {
        try{
            productRegisterService.register(product);
        }catch(Exception e){
            exception = e;
        }
    }

    @Then("新商品は正常に登録される")
    public void 新商品は正常に登録される() {
        Assertions.assertNull(exception);
    }
}
