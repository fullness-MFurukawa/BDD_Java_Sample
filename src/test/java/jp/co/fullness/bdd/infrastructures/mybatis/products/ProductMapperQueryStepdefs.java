package jp.co.fullness.bdd.infrastructures.mybatis.products;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jp.co.fullness.bdd.util.factories.ProductTableModelYamlFactory;
import org.junit.jupiter.api.Assertions;

import java.util.List;

/**
 * 演習-04:ProductMapperインターフェイスの問合せ機能をテストする
 */
public class ProductMapperQueryStepdefs {
    // 商品テーブルアクセスMapperインターフェイス
    private final ProductMapper productMapper;
    // YAML形式からProductTableModelを生成するFactory
    private final ProductTableModelYamlFactory productTableModelYamlFactory;

    /**
     * コンストラクタ
     * @param productMapper 商品テーブルアクセスMapperインターフェイス
     * @param productTableModelYamlFactory YAML形式からProductTableModelを生成するFactory
     */
    public ProductMapperQueryStepdefs(
            ProductMapper productMapper,
            ProductTableModelYamlFactory productTableModelYamlFactory) {
        this.productMapper = productMapper;
        this.productTableModelYamlFactory = productTableModelYamlFactory;
    }

    /**
     * findById(): 指定されたIdの商品を取得する
     */
    private String id;
    private ProductTableModel model;
    @Given("取得対象の商品Idを用意する {string}")
    public void 取得対象の商品idを用意する(String id) {
        this.id = id;
    }

    @When("商品Idで商品を取得する")
    public void 商品idで商品を取得する() {
        model = productMapper.findById(id);
    }

    @Then("商品Idで取得した結果を評価する")
    public void 商品idで取得した結果を評価する(String docString) throws Exception {
        // YAML形式の評価データをProductTableModelに変換する
        var expectedModel = productTableModelYamlFactory.convert(docString);
        Assertions.assertEquals(expectedModel.getObjId(),model.getObjId());
        Assertions.assertEquals(expectedModel.getName(),model.getName());
        Assertions.assertEquals(expectedModel.getPrice(),model.getPrice());
    }

    @Then("商品Idで取得した結果がnullであることを評価する")
    public void 商品idで取得した結果がnullであることを評価する() {
        Assertions.assertNull(model);
    }

    /**
     * findByNameContains(): 指定された商品キーワードに該当する商品を取得する
     */
    private String keyword;
    private List<ProductTableModel> models,expectedModels;
    @Given("キーワードを用意する {string}")
    public void キーワードを用意する(String keyword) {
        this.keyword = keyword;
    }

    @When("キーワードで商品を取得する")
    public void キーワードで商品を取得する() {
        models = productMapper.findByNameContains(keyword);
    }

    @Then("キーワードで取得した結果を評価する")
    public void キーワードで取得した結果を評価する(String docString) throws Exception{
        // YAML形式の評価データをProductTableModelのリストに変換する
        var expectedModels = productTableModelYamlFactory.convertList(docString);
        for (int i = 0; i < expectedModels.size(); i++) {
            Assertions.assertEquals(expectedModels.get(i).getObjId(),
                    models.get(i).getObjId());
            Assertions.assertEquals(expectedModels.get(i).getName(),
                    models.get(i).getName());
            Assertions.assertEquals(expectedModels.get(i).getPrice(),
                    models.get(i).getPrice());
            Assertions.assertEquals(expectedModels.get(i).getCategory().getObjId(),
                    models.get(i).getCategory().getObjId());
            Assertions.assertEquals(expectedModels.get(i).getCategory().getName(),
                    models.get(i).getCategory().getName());
        }
    }

    @Then("キーワードで取得した結果が空であることを評価する")
    public void キーワードで取得した結果が空であることを評価する() {
        Assertions.assertTrue(models.isEmpty());
    }

    /**
     * exists():指定された商品名の存在有無を取得する
     */
    private String name;
    private Boolean result;
    @Given("存在を調べる商品名を用意する {string}")
    public void 存在を調べる商品名を用意する(String name) {
        this.name = name;
    }

    @When("存在を調べる")
    public void 存在を調べる() {
        result = productMapper.exists(name);
    }

    @Then("結果が返されることを評価する {string}")
    public void 結果が返されることを評価する(String expected) {
        Assertions.assertEquals(Boolean.parseBoolean(expected),result);
    }
}
