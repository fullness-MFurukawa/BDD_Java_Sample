package jp.co.fullness.bdd.infrastructures.mybatis.products;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jp.co.fullness.bdd.util.factories.ProductTableModelYamlFactory;
import org.junit.jupiter.api.Assertions;

/**
 * 演習-05:ProductMapperインターフェイスの更新機能をテストする
 */
public class ProductMapperUpdateStepdefs {
    // 商品テーブルをアクセスするMapperインターフェイス
    private final ProductMapper productMapper;
    // YAML形式からProductTableModelを生成するFactory
    private final ProductTableModelYamlFactory productTableModelYamlFactory;

    /**
     * コンストラクタ
     * @param productMapper 商品テーブルをアクセスするMapperインターフェイス
     * @param productTableModelYamlFactory YAML形式からProductTableModelを生成するFactory
     */
    public ProductMapperUpdateStepdefs(
            ProductMapper productMapper,
            ProductTableModelYamlFactory productTableModelYamlFactory) {
        this.productMapper = productMapper;
        this.productTableModelYamlFactory = productTableModelYamlFactory;
    }

    private ProductTableModel model;
    private Exception exception;
    private Boolean result;
    private String id;
    /**
     * create():商品を永続化する
     */
    @Given("新しい商品を用意する")
    public void 新しい商品を用意する(String docString) throws Exception{
        model = productTableModelYamlFactory.convert(docString);
    }

    @When("新しい商品を永続化する")
    public void 新しい商品を永続化する() {
        try{
            productMapper.create(model);
        }catch(Exception e){
            exception = e;
        }
    }

    @Then("商品が永続化されたことを評価する")
    public void 商品が永続化されたことを評価する() {
        Assertions.assertNull(exception);
    }

    /**
     * updateById():指定された商品Idの商品を変更する
     */
    @Given("変更商品を用意する")
    public void 変更商品を用意する(String docString) throws Exception{
        model = productTableModelYamlFactory.convert(docString);
    }

    @When("商品の単価を変更する")
    public void 商品の単価を変更する() {
        try{
            result = productMapper.updateById(model);
        }catch(Exception e){
            exception = e;
        }
    }

    @Then("商品の変更結果を評価する {string}")
    public void 商品の変更結果を評価する(String expected) {
        Assertions.assertEquals(Boolean.parseBoolean(expected),result);
    }
    /**
     * deleteById():指定された商品Idの商品を削除する
     */
    @Given("削除する商品Idを用意する {string}")
    public void 削除する商品idを用意する(String id) {
        this.id = id;
    }

    @When("商品を削除する")
    public void 商品を削除する() {
        try{
            result = productMapper.deleteById(id);
        }catch(Exception e){
            exception = e;
        }
    }

    @Then("商品の削除結果を評価する {string}")
    public void 商品の削除結果を評価する(String expected) {
        Assertions.assertEquals(Boolean.parseBoolean(expected),result);
    }
}
