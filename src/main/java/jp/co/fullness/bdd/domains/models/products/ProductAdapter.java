package jp.co.fullness.bdd.domains.models.products;

import java.util.List;

/**
 * 商品エンティティを他モデルに変換および他モデルから復元するインターフェイス
 * @author Fullness,Inc.
 * @since 2025-02-23
 * @version 1.0.1
 * @param <T> 変換する型
 */
public interface ProductAdapter<T> {
    /**
     * 商品エンティティを他のモデルに変換した結果を返す
     * @param source 商品エンティティ
     * @return 変換結果
     */
    T convert(Product source);

    /**
     * 商品エンティティのリストから他モデルのリストに変換した結果を返す
     * @param sources 商品エンティティリスト
     * @return 変換結果
     */
    List<T> convertList(List<Product> sources);

    /**
     * 他のモデルから商品エンティティを復元する
     * @param source 他のモデル
     * @return 商品エンティティ
     */
    Product restore(T source);

    /**
     * 他モデルのリストから商品エンティティのリストに復元した結果を返す
     * @param sources 他モデルのリスト
     * @return 復元結果
     */
    List<Product> restoreList(List<T> sources);
}
