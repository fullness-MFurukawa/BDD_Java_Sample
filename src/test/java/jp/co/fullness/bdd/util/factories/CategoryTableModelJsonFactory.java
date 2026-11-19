package jp.co.fullness.bdd.util.factories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jp.co.fullness.bdd.infrastructures.mybatis.categories.CategoryTableModel;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

/**
 * JSON形式のデータからCategoryTableModelを生成するFactory
 * @author Fullness,Inc.
 * @since 2025-02-23
 * @version 1.0.1
 */
@Component
public class CategoryTableModelJsonFactory  implements EvaluationDataFactory<String, CategoryTableModel> {
    /**
     * 単一データを変換した結果を返す
     * @param data 変換対象
     * @return 変換結果
     */
    @Override
    public CategoryTableModel convert(String data) throws Exception {
        var objectMapper = new ObjectMapper();
        return objectMapper.readValue(data, CategoryTableModel.class);
    }

    /**
     * 複数データをリストに変換した結果を返す
     * @param data 変換対象
     * @return 変換結果
     */
    @Override
    public List<CategoryTableModel> convertList(String data) throws Exception {
        var objectMapper = new ObjectMapper();
        List<Map<String, String>> jsonData =
                objectMapper.readValue(data, new TypeReference<>() {});
        return jsonData.stream()
                .map(entry -> new CategoryTableModel(
                        null,entry.get("objId"), entry.get("name")))
                .toList();
    }
}
