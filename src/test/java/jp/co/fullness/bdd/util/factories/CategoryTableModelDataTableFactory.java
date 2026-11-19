package jp.co.fullness.bdd.util.factories;

import io.cucumber.datatable.DataTable;
import jp.co.fullness.bdd.infrastructures.mybatis.categories.CategoryTableModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * DataTable形式のデータからCategoryTableModelを生成するFactory
 * 提供
 */
@Component
public class CategoryTableModelDataTableFactory  implements EvaluationDataFactory<DataTable, CategoryTableModel> {
    /**
     * 単一データを変換した結果を返す
     *
     * @param data 変換対象
     * @return 変換結果
     */
    @Override
    public CategoryTableModel convert(DataTable data) throws Exception {
        List<Map<String, String>> rows = data.asMaps(String.class, String.class);
        Map<String, String> firstRow = rows.getFirst(); // 最初の行を取得
        return new CategoryTableModel(null,firstRow.get("objId"),firstRow.get("name"));
    }

    /**
     * 複数データをリストに変換した結果を返す
     *
     * @param data 変換対象
     * @return 変換結果
     */
    @Override
    public List<CategoryTableModel> convertList(DataTable data) throws Exception {
        List<Map<String, String>> rows = data.asMaps(String.class, String.class);
        // 各行をCategoryインスタンスに変換し、リストにして返す
        return rows.stream()
                .map(row -> new CategoryTableModel(null, row.get("objId"), row.get("name")))
                .collect(Collectors.toList());
    }
}
