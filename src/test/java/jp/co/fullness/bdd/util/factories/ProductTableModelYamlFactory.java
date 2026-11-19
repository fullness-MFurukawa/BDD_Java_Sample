package jp.co.fullness.bdd.util.factories;

import jp.co.fullness.bdd.infrastructures.mybatis.categories.CategoryTableModel;
import jp.co.fullness.bdd.infrastructures.mybatis.products.ProductTableModel;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.util.List;
import java.util.Map;

/**
 * YAML形式のデータからProductTableModelを生成するFactory
 * 提供
 */
@Component
public class ProductTableModelYamlFactory implements EvaluationDataFactory<String, ProductTableModel> {
    /**
     * 単一データを変換した結果を返す
     *
     * @param data 変換対象
     * @return 変換結果
     */
    @Override
    public ProductTableModel convert(String data) throws Exception {
        var yaml = new Yaml();
        return yaml.loadAs(data , ProductTableModel.class);
    }

    /**
     * 複数データをリストに変換した結果を返す
     *
     * @param data 変換対象
     * @return 変換結果
     */
    @Override
    public List<ProductTableModel> convertList(String data) throws Exception {
        Yaml yaml = new Yaml();
        // YAMLデータをパースして、List<Map<String, Object>>に変換
        List<Map<String, Object>> yamlData = yaml.load(data);
        // パースされたデータをProductTableModelオブジェクトのリストに変換
        return yamlData.stream().map(entry -> {
            ProductTableModel model = new ProductTableModel();
            model.setObjId((String) entry.get("objId"));
            model.setName((String) entry.get("name"));
            model.setPrice((Integer) entry.get("price"));
            // ネストされたカテゴリデータを取得
            Map<String, Object> categoryData = (Map<String, Object>) entry.get("category");
            if (categoryData != null){
                CategoryTableModel cmodel = new CategoryTableModel();
                cmodel.setObjId((String) categoryData.get("objId"));
                cmodel.setName((String) categoryData.get("name"));
                model.setCategory(cmodel);
            }
            return model;
        }).toList();
    }
}
