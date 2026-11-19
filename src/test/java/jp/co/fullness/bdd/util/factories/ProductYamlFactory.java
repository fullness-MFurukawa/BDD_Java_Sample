package jp.co.fullness.bdd.util.factories;

import jp.co.fullness.bdd.domains.models.categories.Category;
import jp.co.fullness.bdd.domains.models.products.Product;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Yaml形式のデータからProductを生成するFactory
 * 提供
 */
@Component
public class ProductYamlFactory  implements EvaluationDataFactory<String, Product>{
    /**
     * 単一データを変換した結果を返す
     *
     * @param data 変換対象
     * @return 変換結果
     */
    @Override
    public Product convert(String data) throws Exception {
        Yaml yaml = new Yaml();
        Map<String, Object> map = yaml.load(data);// YAML文字列をMapに変換
        Product product = new Product(
                map.get("id").toString(), map.get("name").toString(),
                Integer.parseInt(map.get("price").toString()));
        if (map.containsKey("category")) {
            Map<String, String> categoryMap = (Map<String, String>) map.get("category");
            Category category = new Category(categoryMap.get("id"), categoryMap.get("name"));
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
        Yaml yaml = new Yaml();
        List<Map<String, Object>> list = yaml.load(data); // YAML文字列をList<Map>に変換
        List<Product> products = new ArrayList<>(); // List<Map>からList<Product>に変換
        for (Map<String, Object> map : list) {
            Product product = new Product(map.get("id").toString(), map.get("name").toString(),
                    Integer.parseInt(map.get("price").toString()));
            // カテゴリが存在する場合はCategoryインスタンスを生成して設定
            if (map.containsKey("category")) {
                Map<String, String> categoryMap = (Map<String, String>) map.get("category");
                Category category = new Category(categoryMap.get("id"), categoryMap.get("name"));
                product.changeCategory(category);
            }
        }
        return products;
    }
}
