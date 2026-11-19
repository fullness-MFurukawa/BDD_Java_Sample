package jp.co.fullness.bdd.infrastructures.mybatis.categories;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * categoryテーブルモデル
 * @author Fullness,Inc.
 * @since 2025-02-23
 * @version 1.0.1
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryTableModel {
    private Integer id;     // 主キー
    private String  objId;  // カテゴリId
    private String  name;   // カテゴリ名
}
