package jp.co.fullness.bdd.infrastructures.mybatis.categories;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jp.co.fullness.bdd.util.factories.CategoryTableModelYamlFactory;
import org.junit.jupiter.api.Assertions;
import java.util.List;

/**
 * 商品カテゴリテーブルCRUD操作インターフェイスの単体テスト
 * @author Fullness,Inc.
 * @since 2025-02-23
 * @version 1.0.1
 */
public class CategoryMapperStepdefs {

    // Yaml形式のデータをCategoryTableModelに変換するFactory
    private final CategoryMapper categoryMapper;
    // Yaml形式のデータをCategoryTableModelに変換するFactory
    private final CategoryTableModelYamlFactory categoryTableModelYamlFactory;

    /**
     * コンストラクタ
     * @param categoryMapper Yaml形式のデータをCategoryTableModelに変換するFactory
     * @param categoryTableModelYamlFactory Yaml形式のデータをCategoryTableModelに変換するFactory
     */
    public CategoryMapperStepdefs(
            CategoryMapper categoryMapper,
            CategoryTableModelYamlFactory categoryTableModelYamlFactory) {
        this.categoryMapper = categoryMapper;
        this.categoryTableModelYamlFactory = categoryTableModelYamlFactory;
    }

    /**
     * 指定されたIdの商品カテゴリを取得する
     */
    private String id;
    private CategoryTableModel model;
    @Given("取得対象の商品カテゴリIdを用意する {string}")
    public void 取得対象の商品カテゴリidを用意する(String id) {
        this.id = id;
    }

    @When("商品カテゴリIdで商品カテゴリを取得する")
    public void 商品カテゴリidで商品カテゴリを取得する() {
        model = categoryMapper.findById(id);
    }

    @Then("取得した商品カテゴリを評価する")
    public void 取得した商品カテゴリを評価する(String docString) throws Exception {
        // Yaml形式の評価データをCategoryTableModelに変換する
        var expectedModel = categoryTableModelYamlFactory.convert(docString);
        Assertions.assertEquals(expectedModel.getObjId(),model.getObjId());
        Assertions.assertEquals(expectedModel.getName(),model.getName());
    }

    @Then("取得した商品カテゴリがnullであることを評価する")
    public void 取得した商品カテゴリがnullであることを評価する() {
        Assertions.assertNull(model);
    }

    /**
     * すべてのカテゴリを取得する
     */
    private List<CategoryTableModel> models,expectedModels;
    @Given("取得する商品カテゴリを用意する")
    public void 取得する商品カテゴリを用意する(String docString) throws Exception {
        // 取得予定の商品カテゴリをCategoryTableModelのリストに変換する
        expectedModels = categoryTableModelYamlFactory.convertList(docString);
    }

    @When("すべての商品カテゴリを取得する")
    public void すべての商品カテゴリを取得する() {
        models = categoryMapper.findAll();
    }

    @Then("すべての商品カテゴリが取得されたことを評価する")
    public void すべての商品カテゴリが取得されたことを評価する() {
        // 取得した結果と予測値を検証する
        for (int i = 0; i < expectedModels.size(); i++) {
            Assertions.assertEquals(expectedModels.get(i).getObjId(),
                    models.get(i).getObjId());
            Assertions.assertEquals(expectedModels.get(i).getName(),
                    models.get(i).getName());
        }
    }
}
