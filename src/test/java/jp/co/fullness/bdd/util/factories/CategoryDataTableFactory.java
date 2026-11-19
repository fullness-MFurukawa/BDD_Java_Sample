package jp.co.fullness.bdd.util.factories;

import io.cucumber.datatable.DataTable;
import jp.co.fullness.bdd.domains.models.categories.Category;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * DataTable形式のデータからCategoryを生成するFactory
 * @author Fullness,Inc.
 * @since 2025-02-23
 * @version 1.0.1
 */
@Component
public class CategoryDataTableFactory implements EvaluationDataFactory<DataTable, Category> {
    /**
     * 単一データを変換した結果を返す
     * @param data 変換対象
     * @return 変換結果
     */
    @Override
    public Category convert(DataTable data) throws Exception{
        List<Map<String, String>> rows = data.asMaps(String.class, String.class);
        Map<String, String> firstRow = rows.getFirst(); // 最初の行を取得
        String id = firstRow.get("id");
        String name = firstRow.get("name");
        return new Category(id, name);// Categoryインスタンスを生成して返す
    }

    /**
     * 複数データをリストに変換した結果を返す
     * @param data 変換対象
     * @return 変換結果
     */
    @Override
    public List<Category> convertList(DataTable data) throws Exception{
        List<Map<String, String>> rows = data.asMaps(String.class, String.class);
        // 各行をCategoryインスタンスに変換し、リストにして返す
        return rows.stream()
                .map(row -> new Category(row.get("id"), row.get("name")))
                .collect(Collectors.toList());
    }
}
