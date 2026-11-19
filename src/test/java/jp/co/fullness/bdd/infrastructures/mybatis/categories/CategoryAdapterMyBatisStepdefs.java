package jp.co.fullness.bdd.infrastructures.mybatis.categories;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jp.co.fullness.bdd.domains.models.categories.Category;
import jp.co.fullness.bdd.util.factories.CategoryJsonFactory;
import jp.co.fullness.bdd.util.factories.CategoryTableModelJsonFactory;
import org.junit.jupiter.api.Assertions;
import java.util.List;

/**
 * CategoryエンティティとCategoryTableModelの相互変換クラスの単体テストドライバ
 * @author Fullness,Inc.
 * @since 2025-02-23
 * @version 1.0.1
 */
public class CategoryAdapterMyBatisStepdefs {

    // 商品カテゴリエンティティをテーブルモデルに変換するアダプタ
    private final CategoryAdapterMyBatis categoryAdapter;
    // JSON形式のデータからCategoryを生成するFactory
    private final CategoryJsonFactory categoryJsonFactory;
    // JSON形式のデータからCategoryTableModelを生成するFactory
    private final CategoryTableModelJsonFactory categoryTableModelJsonFactory;

    /**
     * コンストラクタ
     * @param categoryAdapter 商品カテゴリエンティティをテーブルモデルに変換するアダプタ
     * @param categoryJsonFactory JSON形式のデータからCategoryを生成するFactory
     * @param categoryTableModelJsonFactory
     *  JSON形式のデータからCategoryTableModelを生成するFactory
     */
    public CategoryAdapterMyBatisStepdefs(
            CategoryAdapterMyBatis categoryAdapter,
            CategoryJsonFactory categoryJsonFactory,
            CategoryTableModelJsonFactory categoryTableModelJsonFactory) {
        this.categoryAdapter = categoryAdapter;
        this.categoryJsonFactory = categoryJsonFactory;
        this.categoryTableModelJsonFactory = categoryTableModelJsonFactory;
    }

    private Category category;
    private CategoryTableModel model;
    /**
     * Convert():CategoryをCategoryTableModelに変換する
     */
    @Given("Categoryエンティティを用意する")
    public void categoryエンティティを用意する(String jsonString) throws Exception{
        category = categoryJsonFactory.convert(jsonString);
    }

    @When("CategoryエンティティをCategoryTableModelに変換する")
    public void categoryエンティティをcategorytablemodelに変換する() {
        model = categoryAdapter.convert(category);
    }

    @Then("CategoryTableModelの値を検証する")
    public void categorytablemodelの値を検証する(String jsonString) throws Exception{
        var expectedModel = categoryTableModelJsonFactory.convert(jsonString);
        Assertions.assertEquals(expectedModel.getObjId(),model.getObjId());
        Assertions.assertEquals(expectedModel.getName(),model.getName());
    }

    /**
     * Restore():CategoryTableModelからCategoryを復元する
     */
    @Given("CategoryTableModelを用意する")
    public void categorytablemodelを用意する(String jsonString) throws Exception{
        model = categoryTableModelJsonFactory.convert(jsonString);
    }

    @When("CategoryTableModelからCategoryを復元する")
    public void categorytablemodelからcategoryを復元する() {
        category = categoryAdapter.restore(model);
    }

    @Then("Categoryの値を検証する")
    public void categoryの値を検証する(String jsonString) throws Exception{
        var expectedCategory = categoryJsonFactory.convert(jsonString);
        Assertions.assertEquals(
                expectedCategory.getCategoryId().getValue(),
                category.getCategoryId().getValue());
        Assertions.assertEquals(
                expectedCategory.getCategoryName().getValue(),
                category.getCategoryName().getValue());
    }

    private List<Category> categories;
    private List<CategoryTableModel> models;
    /**
     * ConvertList():List<Product>をList<CategoryTableModel>に変換する
     */
    @Given("Categoryのリストを用意する")
    public void categoryのリストを用意する(String jsonString) throws Exception{
        categories = categoryJsonFactory.convertList(jsonString);
    }

    @When("CategoryのリストをCategoryModelのリストに変換する")
    public void categoryのリストをcategorymodelのリストに変換する() {
        models = categoryAdapter.convertList(categories);
    }

    @Then("リスト内のCategoryTableModelのプロパティを検証する")
    public void リスト内のcategorytablemodelのプロパティを検証する(
            String jsonString) throws Exception{
        var expectedModels  = categoryTableModelJsonFactory.convertList(jsonString);
        for (int i = 0; i < expectedModels.size(); i++){
            Assertions.assertEquals(expectedModels.get(i).getObjId(),
                    models.get(i).getObjId());
            Assertions.assertEquals(expectedModels.get(i).getName(),
                    models.get(i).getName());
        }
    }

    /**
     * RestoreList:List<CategoryTableModel>からList<Category>を復元する
     */
    @Given("CategoryTableModelのリストを用意する")
    public void categorytablemodelのリストを用意する(String jsonString) throws Exception{
        models = categoryTableModelJsonFactory.convertList(jsonString);
    }

    @When("CategoryTableModelのリストからCategoryのリストを復元する")
    public void categorytablemodelのリストからcategoryのリストを復元する() {
        categories = categoryAdapter.restoreList(models);
    }

    @Then("リスト内のCategoryの値を検証する")
    public void リスト内のcategoryの値を検証する(String jsonString) throws Exception{
        var expectedCategories = categoryJsonFactory.convertList(jsonString);
        for (int i = 0; i < expectedCategories.size() ; i++){
            Assertions.assertEquals(
                    expectedCategories.get(i).getCategoryId().getValue(),
                    categories.get(i).getCategoryId().getValue());
            Assertions.assertEquals(
                    expectedCategories.get(i).getCategoryName().getValue(),
                    categories.get(i).getCategoryName().getValue());
        }
    }
}
