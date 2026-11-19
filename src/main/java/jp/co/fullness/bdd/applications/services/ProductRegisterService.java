package jp.co.fullness.bdd.applications.services;

import jp.co.fullness.bdd.domains.models.categories.Category;
import jp.co.fullness.bdd.domains.models.categories.CategoryId;
import jp.co.fullness.bdd.domains.models.products.Product;
import jp.co.fullness.bdd.domains.models.products.ProductName;

import java.util.List;

/**
 * 商品登録サービスインターフェイス
 * @author Fullness,Inc.
 * @since 2025-02-23
 * @version 1.0.1
 */
public interface ProductRegisterService {
    /**
     * すべての商品カテゴリを取得する
     * @return 商品カテゴリリスト
     */
    List<Category> getCategories();

    /**
     * 指定された商品カテゴリIdの商品カテゴリを取得する
     * @param categoryId 商品カテゴリId
     * @return 商品カテゴリ
     */
    Category getCategory(CategoryId categoryId);

    /**
     * 指定された商品の有無を調べる
     * @param productName 有無を調べる商品名
     */
    void exists(ProductName productName);

    /**
     * 指定された商品を永続化する
     * @param product 永続化する商品
     */
    void register(Product product);
}
