package jp.co.fullness.bdd.domains.models.categories;

import java.util.List;

/**
 * 商品カテゴリエンティティを他のモデルに変換および復元するインターフェイス
 * @author Fullness,Inc.
 * @version 1.0
 * @since 2024/10/25
 * @param <T> 変換する型
 */
public interface CategoryAdapter<T> {
    /**
     * 商品カテゴリエンティティを他のモデルに変換した結果を返す
     * @param source 商品カテゴリエンティティ
     * @return 変換結果
     */
    T convert(Category source);
    /**
     * 商品カテゴリエンティティリストを他のモデルのリストに変換した結果を返す
     * @param sources 商品カテゴリのリスト
     * @return 変換結果
     */
    List<T> convertList(List<Category> sources);
    /**
     * 他のモデルから商品カテゴリエンティティを復元する
     * @param source 他のモデル
     * @return 復元結果
     */
    Category restore(T source);
    /**
     * 他のモデルのリストから商品カテゴリエンティティのリストを復元する
     * @param sources 他のモデルのリスト
     * @return 復元結果
     */
    List<Category> restoreList(List<T> sources);
}
