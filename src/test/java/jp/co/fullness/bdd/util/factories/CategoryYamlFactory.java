package jp.co.fullness.bdd.util.factories;

import jp.co.fullness.bdd.domains.models.categories.Category;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * YAML形式のデータからCategoryを生成するFactory
 * 提供
 */
@Component
public class CategoryYamlFactory  implements EvaluationDataFactory<String, Category> {
    /**
     * 単一データを変換した結果を返す
     *
     * @param data 変換対象
     * @return 変換結果
     */
    @Override
    public Category convert(String data) throws Exception {
        // Yamlインスタンスを作成
        Yaml yaml = new Yaml();
        // YAML文字列をMapに変換
        Map<String, String> map = yaml.load(data);
        // Categoryインスタンスを生成して返す
        return new Category(map.get("id"), map.get("name"));
    }

    /**
     * 複数データをリストに変換した結果を返す
     *
     * @param data 変換対象
     * @return 変換結果
     */
    @Override
    public List<Category> convertList(String data) throws Exception {
        // Yamlインスタンスを作成
        Yaml yaml = new Yaml();
        // YAML文字列をList<Map>に変換
        List<Map<String, String>> list = yaml.load(data);
        // List<Map>からList<Category>に変換
        return list.stream()
                .map(entry -> new Category(entry.get("id"), entry.get("name")))
                .collect(Collectors.toList());
    }
}
