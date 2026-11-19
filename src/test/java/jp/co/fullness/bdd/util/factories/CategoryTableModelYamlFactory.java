package jp.co.fullness.bdd.util.factories;

import jp.co.fullness.bdd.infrastructures.mybatis.categories.CategoryTableModel;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;
import java.util.List;
import java.util.Map;

/**
 * YAML形式のデータからCategoryTableModelを生成するFactory
 * @author Fullness,Inc.
 * @since 2025-02-23
 * @version 1.0.1
 */
@Component
public class CategoryTableModelYamlFactory
        implements EvaluationDataFactory<String, CategoryTableModel>{
    /**
     * 単一データを変換した結果を返す
     * @param data 変換対象
     * @return 変換結果
     */
    @Override
    public CategoryTableModel convert(String data) throws Exception {
        var yaml = new Yaml();
        return yaml.loadAs(data , CategoryTableModel.class);
    }

    /**
     * 複数データをリストに変換した結果を返す
     * @param data 変換対象
     * @return 変換結果
     */
    @Override
    public List<CategoryTableModel> convertList(String data) throws Exception {
        var yaml = new Yaml();
        List<Map<String, String>> yamlData = yaml.load(data);
        return yamlData.stream()
                .map(entry -> {
                    return new CategoryTableModel(
                            null, entry.get("objId"),entry.get("name"));
                }).toList();
    }
}
