package jp.co.fullness.bdd.domains.models.categories;

import java.util.List;

/**
 * 商品カテゴリエンティティをアクセスするリポジトリインターフェイス
 * @author Fullness,Inc.
 * @version 1.0
 * @since 2024/10/25
 */
public interface CategoryRepository {
    /**
     * すべての商品カテゴリを取得する
     * @return 取得した商品カテゴリのリスト
     */
    List<Category> findAll();
    /**
     * 指定されたカテゴリIdの商品カテゴリを取得する
     * @param categoryId 取得対象を表すカテゴリId
     * @return 取得した商品カテゴリ
     */
    Category findById(CategoryId categoryId);
}
