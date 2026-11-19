package jp.co.fullness.bdd.infrastructures.mybatis.products;

import jp.co.fullness.bdd.domains.models.categories.CategoryAdapter;
import jp.co.fullness.bdd.domains.models.products.Product;
import jp.co.fullness.bdd.domains.models.products.ProductAdapter;
import jp.co.fullness.bdd.infrastructures.mybatis.categories.CategoryTableModel;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品エンティティをテーブルモデルに変換および復元するクラス
 * @author Fullness,Inc.
 * @since 2025-02-23
 * @version 1.0.1
 */
@Component
public class ProductAdapterMyBatis implements ProductAdapter<ProductTableModel> {
    // カテゴリ変換アダプタ
    private final CategoryAdapter<CategoryTableModel> categoryAdapter;

    /**
     * コンストラクタ
     * @param categoryAdapter カテゴリ変換アダプタ
     */
    public ProductAdapterMyBatis(CategoryAdapter<CategoryTableModel> categoryAdapter) {
        this.categoryAdapter = categoryAdapter;
    }
    /**
     * 商品エンティティを他のモデルに変換した結果を返す
     * @param source 商品エンティティ
     * @return 変換結果
     */
    @Override
    public ProductTableModel convert(Product source) {
        var model = new ProductTableModel();
        model.setObjId(source.getProductId().getValue());
        model.setName(source.getProductName().getValue());
        model.setPrice(source.getProductPrice().getValue());
        if (source.getCategory() != null){
            model.setCategory(categoryAdapter.convert(source.getCategory()));
        }
        return  model;
    }
    /**
     * 商品エンティティのリストから他モデルのリストに変換した結果を返す
     * @param sources 商品エンティティリスト
     * @return 変換結果
     */
    @Override
    public List<ProductTableModel> convertList(List<Product> sources) {
        if (sources == null || sources.isEmpty()){
            return null;
        }
        var models = new ArrayList<ProductTableModel>();
        sources.forEach(p -> models.add(this.convert(p)));
        return models;
    }
    /**
     * 他のモデルから商品エンティティを復元する
     * @param source 他のモデル
     * @return 商品エンティティ
     */
    @Override
    public Product restore(ProductTableModel source) {
        var product = new Product(source.getObjId(),source.getName(), source.getPrice());
        if (source.getCategory()!= null){
            product.changeCategory(categoryAdapter.restore(source.getCategory()));
        }
        return product;
    }
    /**
     * 他モデルのリストから商品エンティティのリストに復元した結果を返す
     * @param sources 他モデルのリスト
     * @return 復元結果
     */
    @Override
    public List<Product> restoreList(List<ProductTableModel> sources) {
        if (sources == null || sources.isEmpty()){
            return null;
        }
        var products = new ArrayList<Product>();
        sources.forEach(m -> products.add(this.restore(m)));
        return products;
    }
}
