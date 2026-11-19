package jp.co.fullness.bdd.infrastructures.mybatis.products;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jp.co.fullness.bdd.domains.models.products.Product;
import jp.co.fullness.bdd.domains.models.products.ProductId;
import jp.co.fullness.bdd.domains.models.products.ProductName;
import jp.co.fullness.bdd.domains.models.products.ProductRepository;
import jp.co.fullness.bdd.util.factories.ProductYamlFactory;
import org.junit.jupiter.api.Assertions;
import java.util.List;

/**
 * 演習-09:シナリオとテストドライバを作成しテストする
 */
public class ProductRepositoryMyBatisStepdefs {
    // ProductRepositoryインターフェイスの実装
    private final ProductRepository productRepository;
    // YAML形式からProductを生成するFactory
    private final ProductYamlFactory productYamlFactory;
    /**
     * コンストラクタ
     * @param productRepository ProductRepositoryインターフェイスの実装
     * @param productYamlFactory YAML形式からProductを生成するFactory
     */
    public ProductRepositoryMyBatisStepdefs(
            ProductRepository productRepository,
            ProductYamlFactory productYamlFactory) {
        this.productRepository = productRepository;
        this.productYamlFactory = productYamlFactory;
    }

    /**
     * findById(): 指定された商品Idの商品を取得する
     */
    private ProductId productId;
    private Product product;
    @Given("取得する商品Idを用意する {string}")
    public void 取得する商品idを用意する(String id) {
        productId = new ProductId(id);
    }

    @When("商品Idで永続化層から商品を取得する")
    public void 商品idで永続化層から商品を取得する() {
        product = productRepository.findById(productId);
    }

    @Then("取得した商品を評価する")
    public void 取得した商品を評価する(String yamlString) throws Exception{
        var expectedProduct = productYamlFactory.convert(yamlString);
        Assertions.assertEquals(expectedProduct.getProductId(),
                product.getProductId());
        Assertions.assertEquals(expectedProduct.getProductName(),
                product.getProductName());
        Assertions.assertEquals(expectedProduct.getProductPrice(),
                product.getProductPrice());
    }

    @Then("取得した商品がnullであることを評価する")
    public void 取得した商品がnullであることを評価する() {
        Assertions.assertNull(product);
    }

    /**
     * findByNameContains(): 指定された商品キーワードに該当する商品を取得する
     */
    private ProductName _keyword;
    private List<Product> products;
    @Given("検索キーワードを用意する {string}")
    public void 検索キーワードを用意する(String keyword) {
        _keyword = new ProductName(keyword);
    }

    @When("キーワードで永続化層から商品を取得する")
    public void キーワードで永続化層から商品を取得する() {
        products = productRepository.findByNameContains(_keyword);
    }

    @Then("キーワードで取得した商品を評価する")
    public void キーワードで取得した商品を評価する(String yamlString) throws Exception{
        var expectedProducts = productYamlFactory.convertList(yamlString);
        for (int i = 0 ; i < expectedProducts.size(); i++){
            Assertions.assertEquals(expectedProducts.get(i).getProductId() ,
                    products.get(i).getProductId());
            Assertions.assertEquals(expectedProducts.get(i).getProductName() ,
                    products.get(i).getProductName());
            Assertions.assertEquals(expectedProducts.get(i).getProductPrice() ,
                    products.get(i).getProductPrice());
        }
    }

    @Then("キーワードで取得した結果がnullであることを評価する")
    public void キーワードで取得した結果がnullであることを評価する() {
        Assertions.assertNull(products);
    }

    /**
     * exists():指定された商品名の存在有無を取得する
     */
    private ProductName productName;
    private Boolean result;
    @Given("存在確認する商品名を用意する {string}")
    public void 存在確認する商品名を用意する(String name) {
        productName = new ProductName(name);
    }

    @When("商品名で永続化層の商品有無を調べる")
    public void 商品名で永続化層の商品有無を調べる() {
        result = productRepository.exists(productName);
    }

    @Then("存在を評価する {string}")
    public void 存在を評価する(String expected) {
        Assertions.assertEquals(Boolean.parseBoolean(expected),result);
    }

    /**
     * create():Productエンティティを永続化する
     */
    private Exception exception;
    @Given("新しい永続化商品を用意する")
    public void 新しい永続化商品を用意する(String yamlString) throws Exception {
        product = productYamlFactory.convert(yamlString);
    }

    @When("新しい商品を永続化層に追加する")
    public void 新しい商品を永続化層に追加する() {
        try{
            productRepository.create(product);
        }catch(Exception e){
            exception = e;
        }
    }

    @Then("新しい商品が永続化されたことを評価する")
    public void 新しい商品が永続化されたことを評価する() {
        Assertions.assertNull(exception);
    }

    /**
     * updateById():指定された商品IdのProductエンティティを変更する
     */
    @Given("変更する商品を用意する")
    public void 変更する商品を用意する(String yamlString) throws Exception {
        product = productYamlFactory.convert(yamlString);
    }

    @When("永続化層の商品を変更する")
    public void 永続化層の商品を変更する() {
        result = productRepository.updateById(product);
    }

    @Then("永続化層の商品変更結果を評価する {string}")
    public void 永続化層の商品変更結果を評価する(String expected) {
        Assertions.assertEquals(Boolean.parseBoolean(expected), result);
    }

    /**
     * deleteById():指定された商品IdのProductエンティティを削除する
     */
    @Given("削除対象の商品Idを用意する {string}")
    public void 削除対象の商品idを用意する(String id) {
        productId = new ProductId(id);
    }

    @When("永続化層から商品を削除する")
    public void 永続化層から商品を削除する() {
        result = productRepository.deleteById(productId);
    }

    @Then("永続化層の商品削除結果を評価する {string}")
    public void 永続化層の商品削除結果を評価する(String expected) {
        Assertions.assertEquals(Boolean.parseBoolean(expected),result);
    }

}
