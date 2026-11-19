package jp.co.fullness.bdd.applications.services;

import jp.co.fullness.bdd.domains.models.products.Product;
import jp.co.fullness.bdd.domains.models.products.ProductId;

/**
 * 商品変更サービスインターフェイス
 * @author Fullness,Inc.
 * @since 2025-02-23
 * @version 1.0.1
 */
public interface ProductUpdateService {
    /**
     * 指定された商品Idの商品を取得する
     * @param productId 商品Id
     * @return 取得した商品
     */
    Product getProduct(ProductId productId);

    /**
     * 指定された商品を変更する
     * @param product 変更する商品
     */
    void update(Product product);
}
