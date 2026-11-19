package jp.co.fullness.bdd.util.factories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jp.co.fullness.bdd.domains.models.categories.Category;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

/**
 * JSON形式のデータからCategoryを生成するFactory
 * @author Fullness,Inc.
 * @since 2025-02-23
 * @version 1.0.1
 */
@Component
public class CategoryJsonFactory implements EvaluationDataFactory<String, Category>{
    /**
     * 単一データを変換した結果を返す
     * @param data 変換対象
     * @return 変換結果
     */
    @Override
    public Category convert(String data) throws Exception {
        var objectMapper = new ObjectMapper();
        Map<String, String> jsonData = objectMapper.readValue(data, Map.class);
        return new Category(jsonData.get("id"), jsonData.get("name"));
    }

    /**
     * 複数データをリストに変換した結果を返す
     * @param data 変換対象
     * @return 変換結果
     */
    @Override
    public List<Category> convertList(String data) throws Exception {
        var objectMapper = new ObjectMapper();
        List<Map<String, String>> jsonData =
                objectMapper.readValue(data, new TypeReference<>() {});
        return jsonData.stream()
                .map(entry -> new Category(entry.get("id"), entry.get("name")))
                .toList();
    }
}
