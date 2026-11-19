package jp.co.fullness.bdd.infrastructures.mybatis.categories;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jp.co.fullness.bdd.domains.models.categories.Category;
import jp.co.fullness.bdd.domains.models.categories.CategoryId;
import jp.co.fullness.bdd.domains.models.categories.CategoryRepository;
import jp.co.fullness.bdd.util.factories.CategoryDataTableFactory;
import jp.co.fullness.bdd.util.factories.CategoryJsonFactory;
import org.junit.jupiter.api.Assertions;
import java.util.List;

/**
 * CategoryRepositoryインターフェイスを実装した
 * CategoryRepositoryMyBatisのテストドライバ
 * @author Fullness,Inc.
 * @since 2025-02-23
 * @version 1.0.1
 */
public class CategoryRepositoryMyBatisStepdefs {
    // CategoryRepositoryインターフェイス
    private final CategoryRepository categoryRepository;
    // DataTable形式のデータからCategoryを生成するFactory
    private final CategoryDataTableFactory categoryDataTableFactory;
    // JSON形式のデータからCategoryを生成するFactory
    private final CategoryJsonFactory categoryJsonFactory;

    /**
     * コンストラクタ
     * @param categoryRepository CategoryRepositoryインターフェイス
     * @param categoryDataTableFactory DataTable形式のデータからCategoryを生成するFactory
     * @param categoryJsonFactory JSON形式のデータからCategoryを生成するFactory
     */
    public CategoryRepositoryMyBatisStepdefs(
            CategoryRepository categoryRepository,
            CategoryDataTableFactory categoryDataTableFactory,
            CategoryJsonFactory categoryJsonFactory) {
        this.categoryRepository = categoryRepository;
        this.categoryDataTableFactory = categoryDataTableFactory;
        this.categoryJsonFactory = categoryJsonFactory;
    }

    /**
     * findById(): 指定されたIdの商品カテゴリを取得する
     */
    private CategoryId categoryId;
    private Category category;
    @Given("商品カテゴリIdを用意する {string}")
    public void 商品カテゴリidを用意する(String id) {
        categoryId = new CategoryId(id);
    }

    @When("用意した商品カテゴリIdでカテゴリを永続化層から取得する")
    public void 用意した商品カテゴリidでカテゴリを永続化層から取得する() {
        category = categoryRepository.findById(categoryId);
    }

    @Then("取得した商品カテゴリが予測値と等価であることを評価する")
    public void 取得した商品カテゴリが予測値と等価であることを評価する(DataTable dataTable)
    throws Exception{
        var expectedCategory = categoryDataTableFactory.convert(dataTable);
        Assertions.assertEquals(expectedCategory.getCategoryId().getValue() ,
                category.getCategoryId().getValue());
        Assertions.assertEquals(expectedCategory.getCategoryName().getValue() ,
                category.getCategoryName().getValue());
    }

    /**
     * findAll(): すべての商品カテゴリを取得する
     */
    private List<Category> categories,expectedCategories;
    @Given("取得される商品カテゴリを用意する")
    public void 取得される商品カテゴリを用意する(String jsonString) throws Exception {
        expectedCategories = categoryJsonFactory.convertList(jsonString);
    }

    @When("すべての商品カテゴリを永続化層から取得する")
    public void すべての商品カテゴリを永続化層から取得する() {
        categories = categoryRepository.findAll();
    }

    @Then("用意された商品カテゴリが取得できたことを評価する")
    public void 用意された商品カテゴリが取得できたことを評価する() {
        for (int i = 0; i < expectedCategories.size(); i++){
            // equals()メソッドで結果を評価する
            Assertions.assertTrue(
                    expectedCategories.get(i).equals(categories.get(i)));
        }
    }
}
