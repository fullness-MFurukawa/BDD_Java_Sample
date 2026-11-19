package jp.co.fullness.bdd.util.factories;

import io.cucumber.datatable.DataTable;
import jp.co.fullness.bdd.domains.models.categories.Category;
import jp.co.fullness.bdd.domains.models.products.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * DataTable形式のデータからProductを生成するFactory
 * 提供
 */
@Component
public class ProductDataTableFactory implements EvaluationDataFactory<DataTable, Product>{
    /**
     * 単一データを変換した結果を返す
     *
     * @param data 変換対象
     * @return 変換結果
     */
    @Override
    public Product convert(DataTable data) throws Exception {
        Map<String, String> row = data.asMaps(String.class, String.class).getFirst();
        var product = new Product(row.get("id"), row.get("name"), Integer.valueOf(row.get("price")));
        if (row.containsKey("categoryId") && row.containsKey("categoryName")
                && row.get("categoryId") != null && row.get("categoryName") != null) {
            Category category = new Category(row.get("categoryId"), row.get("categoryName"));
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
    public List<Product> convertList(DataTable data) throws Exception {
        List<Map<String, String>> rows = data.asMaps(String.class, String.class);
        var products = new ArrayList<Product>();
        for (Map<String, String> row : rows) {
            Product product = new Product(row.get("id"), row.get("name"),
                    Integer.valueOf(row.get("price")));
            if (row.containsKey("categoryId") && row.containsKey("categoryName")
                    && row.get("categoryId") != null && row.get("categoryName") != null) {
                Category category = new Category(row.get("categoryId"), row.get("categoryName"));
                product.changeCategory(category);
            }
            products.add(product);
        }
        return products;
    }
}
