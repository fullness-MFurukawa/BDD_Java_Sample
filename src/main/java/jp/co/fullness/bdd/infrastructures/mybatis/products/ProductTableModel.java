package jp.co.fullness.bdd.infrastructures.mybatis.products;

import jp.co.fullness.bdd.infrastructures.mybatis.categories.CategoryTableModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * productテーブルモデル
 * 提供
 * @author Fullness,Inc.
 * @version 1.0
 * @since 2024/10/25
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductTableModel {
    private Integer id;                  // 主キー
    private String  objId;               // 商品Id
    private String  name;                // 商品名
    private Integer price;               // 商品単価
    private Integer categoryId;          // 商品カテゴリId(外部キー)未使用
    private CategoryTableModel category; // 商品カテゴリ
}
