package jp.co.fullness.bdd.util.factories;

import io.cucumber.datatable.DataTable;
import jp.co.fullness.bdd.infrastructures.mybatis.categories.CategoryTableModel;
import jp.co.fullness.bdd.infrastructures.mybatis.products.ProductTableModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * DataTable形式のデータからProductTableModelを生成するFactory
 * 提供
 */
@Component
public class ProductTableModelDataTableFactory  implements EvaluationDataFactory<DataTable, ProductTableModel>{
    /**
     * 単一データを変換した結果を返す
     *
     * @param data 変換対象
     * @return 変換結果
     */
    @Override
    public ProductTableModel convert(DataTable data) throws Exception {
        Map<String, String> row = data.asMaps(String.class, String.class).getFirst();
        var model = new ProductTableModel();
        model.setObjId(row.get("objId"));
        model.setName(row.get("name"));
        model.setPrice(Integer.valueOf(row.get("price")));
        if (row.containsKey("categoryId") && row.containsKey("categoryName")
                && row.get("categoryId") != null && row.get("categoryName") != null) {
            String categoryId = row.get("categoryId");
            String categoryName = row.get("categoryName");
            var categoryModel = new CategoryTableModel();
            categoryModel.setObjId(row.get("categoryId"));
            categoryModel.setName(row.get("categoryName"));
            model.setCategory(categoryModel);
        }
        return model;
    }

    /**
     * 複数データをリストに変換した結果を返す
     *
     * @param data 変換対象
     * @return 変換結果
     */
    @Override
    public List<ProductTableModel> convertList(DataTable data) throws Exception {
        List<Map<String, String>> rows = data.asMaps(String.class, String.class);
        var models = new ArrayList<ProductTableModel>();
        for (Map<String, String> row : rows) {
            var model = new ProductTableModel();
            model.setObjId(row.get("objId"));
            model.setName(row.get("name"));
            model.setPrice(Integer.valueOf(row.get("price")));
            if (row.containsKey("categoryId") && row.containsKey("categoryName")
                    && row.get("categoryId") != null && row.get("categoryName") != null) {
                var categoryModel = new CategoryTableModel();
                categoryModel.setObjId(row.get("categoryId"));
                categoryModel.setName(row.get("categoryName"));
                model.setCategory(categoryModel);
            }
            models.add(model);
        }
        return models;
    }
}
