package jp.co.fullness.bdd.infrastructures.mybatis.products;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jp.co.fullness.bdd.domains.models.products.Product;
import jp.co.fullness.bdd.domains.models.products.ProductAdapter;
import jp.co.fullness.bdd.util.factories.ProductJsonFactory;
import jp.co.fullness.bdd.util.factories.ProductTableModelJsonFactory;
import org.junit.jupiter.api.Assertions;

import java.util.List;

/**
 * 演習-07:シナリオとテストドライバを作成し、メソッド単位でテストする
 */
public class ProductAdapterMyBatisStepdefs {
    // ProductとProductTableModelの相互変換Adapter
    private final ProductAdapter<ProductTableModel> productAdapter;
    // JSON形式からProductへの変換Factory
    private final ProductJsonFactory productJsonFactory;
    // JSON形式からProductTableModelへの変換Factory
    private final ProductTableModelJsonFactory productTableModelJsonFactory;

    /**
     * コンストラクタ
     * @param productAdapter　ProductとProductTableModelの相互変換Adapter
     * @param productJsonFactory　JSON形式からProductへの変換Factory
     * @param productTableModelJsonFactory　JSON形式からProductTableModelへの変換Factory
     */
    public ProductAdapterMyBatisStepdefs(
            ProductAdapter<ProductTableModel> productAdapter,
            ProductJsonFactory productJsonFactory,
            ProductTableModelJsonFactory productTableModelJsonFactory) {
        this.productAdapter = productAdapter;
        this.productJsonFactory = productJsonFactory;
        this.productTableModelJsonFactory = productTableModelJsonFactory;
    }
    private Product product;
    private ProductTableModel model;
    /**
     * convert():ProductをProductTableModelに変換する
     */
    @Given("Categoryを保持したProductを用意する")
    public void categoryを保持したproductを用意する(String jsonString) throws Exception {
        product = productJsonFactory.convert(jsonString);
    }

    @When("ProductTableModelを生成する")
    public void producttablemodelを生成する() {
        model = productAdapter.convert(product);
    }

    @Then("ProductTableModelの値を検証する")
    public void producttablemodelの値を検証する(String jsonString) throws Exception {
        // JSONデータをProductTableModelにマッピング
        var expectedModels = productTableModelJsonFactory.convert(jsonString);
        Assertions.assertEquals(expectedModels.getObjId(),model.getObjId());
        Assertions.assertEquals(expectedModels.getName(),model.getName());
        Assertions.assertEquals(expectedModels.getPrice(),model.getPrice());
        Assertions.assertEquals(expectedModels.getCategory().getObjId(),
                model.getCategory().getObjId());
        Assertions.assertEquals(expectedModels.getCategory().getName(),
                model.getCategory().getName());
    }

    /**
     * restore():ProductTableModelからProductを復元する
     */
    @Given("ProductTableModelを用意する")
    public void producttablemodelを用意する(String jsonString) throws Exception {
        model = productTableModelJsonFactory.convert(jsonString);
    }

    @When("Productを復元する")
    public void productを復元する() {
        product = productAdapter.restore(model);
    }

    @Then("Productの値を評価する")
    public void productの値を評価する(String jsonString) throws Exception{
        var expectedProduct = productJsonFactory.convert(jsonString);
        Assertions.assertEquals(
                expectedProduct.getProductId().getValue(),
                product.getProductId().getValue());
        Assertions.assertEquals(
                expectedProduct.getProductName().getValue(),
                product.getProductName().getValue());
        Assertions.assertEquals(
                expectedProduct.getProductPrice().getValue(),
                product.getProductPrice().getValue());
        Assertions.assertEquals(
                expectedProduct.getCategory().getCategoryId().getValue(),
                product.getCategory().getCategoryId().getValue());
        Assertions.assertEquals(
                expectedProduct.getCategory().getCategoryName().getValue(),
                product.getCategory().getCategoryName().getValue());
    }

    private List<Product> products;
    private List<ProductTableModel> models;
    /**
     *  convertList():List<Product>をList<ProductTableModel>に変換する
     */
    @Given("Productを保持したリストを用意する")
    public void productを保持したリストを用意する(String jsonString) throws Exception {
        products = productJsonFactory.convertList(jsonString);
    }

    @When("ProductのリストをProductTableModelのリストに変換する")
    public void productのリストをproducttablemodelのリストに変換する() {
        models = productAdapter.convertList(products);
    }

    @Then("リスト内のProductTableModelのプロパティを評価する")
    public void リスト内のproducttablemodelのプロパティを評価する(String jsonString)
    throws Exception{
        var expectedModels = productTableModelJsonFactory.convertList(jsonString);
        Assertions.assertEquals(3, models.size());
        for (int i = 0; i < expectedModels.size(); i++){
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

    /**
     * restoreList():List<ProductTableModel>からList<Product>を復元する
     */
    @Given("ProductTableModelを保持したリストを用意する")
    public void producttablemodelを保持したリストを用意する(String jsonString)
            throws Exception {
        models =  productTableModelJsonFactory.convertList(jsonString);
    }

    @When("ProductTableModelのリストからProductのリストを復元する")
    public void producttablemodelのリストからproductのリストを復元する() {
        products = productAdapter.restoreList(models);
    }

    @Then("リスト内のドメインモデルProductの値を評価する")
    public void リスト内のドメインモデルproductの値を評価する(String jsonString)
    throws  Exception{
        var expectedProducts = productJsonFactory.convertList(jsonString);
        for (int i = 0; i < expectedProducts.size(); i++){
            Assertions.assertEquals(
                    expectedProducts.get(i).getProductId().getValue(),
                    products.get(i).getProductId().getValue());
            Assertions.assertEquals(
                    expectedProducts.get(i).getProductName().getValue(),
                    products.get(i).getProductName().getValue());
            Assertions.assertEquals(
                    expectedProducts.get(i).getProductPrice().getValue(),
                    products.get(i).getProductPrice().getValue());
            Assertions.assertEquals(
                    expectedProducts.get(i).getCategory().getCategoryId().getValue(),
                    products.get(i).getCategory().getCategoryId().getValue());
            Assertions.assertEquals(
                    expectedProducts.get(i).getCategory().getCategoryName().getValue(),
                    products.get(i).getCategory().getCategoryName().getValue());
        }
    }
}
