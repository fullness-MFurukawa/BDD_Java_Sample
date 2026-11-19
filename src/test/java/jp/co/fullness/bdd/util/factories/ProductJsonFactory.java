package jp.co.fullness.bdd.util.factories;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jp.co.fullness.bdd.domains.models.categories.Category;
import jp.co.fullness.bdd.domains.models.products.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * JSON形式のデータからProductを生成するFactory
 * 提供
 */
@Component
public class ProductJsonFactory  implements EvaluationDataFactory<String, Product>{
    /**
     * 単一データを変換した結果を返す
     *
     * @param data 変換対象
     * @return 変換結果
     */
    @Override
    public Product convert(String data) throws Exception {
        var objectMapper = new ObjectMapper();
        // JSONデータをJsonNodeにパースする
        JsonNode rootNode = objectMapper.readTree(data);
        // Productインスタンスを生成する
        var product = new Product(rootNode.get("id").asText(),
                rootNode.get("name").asText(), rootNode.get("price").asInt());
        // Categoryの情報を取得する
        JsonNode categoryNode = rootNode.get("category");
        if (categoryNode != null) {
            // Categoryインスタンスを生成
            var category = new Category(categoryNode.get("id").asText(),
                    categoryNode.get("name").asText());
            product.changeCategory(category);
        }
        return product;
    }

    /**
     * 複数データをリストに変換した結果を返す
     *
     * @param data 変換対象
     * @return 変換結果
     */
    @Override
    public List<Product> convertList(String data) throws Exception {
        var objectMapper = new ObjectMapper();
        // JSONデータをJsonNodeにパース
        JsonNode rootNode = objectMapper.readTree(data);
        // JsonNodeからリストにマッピング
        var products = new ArrayList<Product>();
        for (JsonNode productNode : rootNode) {
            var p = new Product(productNode.get("id").asText(),
                    productNode.get("name").asText(), productNode.get("price").asInt());
            JsonNode categoryNode = productNode.get("category");
            if (categoryNode != null){
                var category = new Category(categoryNode.get("id").asText(),
                        categoryNode.get("name").asText());
                p.changeCategory(category);
            }
            products.add(p);
        }
        return products;
    }
}
