package jp.co.fullness.bdd.applications.services;

import jp.co.fullness.bdd.domains.models.products.Product;
import jp.co.fullness.bdd.domains.models.products.ProductName;

import java.util.List;

/**
 * 商品キーワード検索サービスインターフェイス
 * @author Fullness,Inc.
 * @since 2025-02-23
 * @version 1.0.1
 */
public interface ProductKeywordSearchService {
    /**
     * 商品キーワード検索
     * @param keyword 商品キーワード
     * @return キーワード検索結果
     */
    List<Product> execute(ProductName keyword);
}
