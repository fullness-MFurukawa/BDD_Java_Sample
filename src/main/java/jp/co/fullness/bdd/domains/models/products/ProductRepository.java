package jp.co.fullness.bdd.domains.models.products;

import java.util.List;

/**
 * 商品ティティをアクセスするリポジトリインターフェイス
 * @author Fullness,Inc.
 * @since 2025-02-23
 * @version 1.0.1
 */
public interface ProductRepository {
    /**
     * 指定された商品Idの商品を取得する
     * @param productId 取得対象を表す商品Id
     * @return 取得した商品
     */
    Product findById(ProductId productId);
    /**
     * キーワードに部分一致する商品を取得する
     * @param partialName 商品キーワード
     * @return 取得結果
     */
    List<Product> findByNameContains(ProductName partialName);
    /**
     * 商品の存在有無確認結果を返す
     * @param productName 商品名
     * @return true:存在する false:存在しない
     */
    Boolean exists(ProductName productName);

    /**
     * 商品を永続化する
     * @param product 永続化する商品
     */
    void create(Product product);

    /**
     * 商品を変更する
     * @param product 変更商品
     * @return true:変更成功 false:変更失敗
     */
    Boolean updateById(Product product);

    /**
     * 指定された商品Idの商品を削除する
     * @param productId 削除対象の商品Id
     * @return true:変更成功 false:変更失敗
     */
    Boolean deleteById(ProductId productId);
}
