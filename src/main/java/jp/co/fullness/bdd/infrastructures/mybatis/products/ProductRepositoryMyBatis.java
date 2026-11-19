package jp.co.fullness.bdd.infrastructures.mybatis.products;

import jp.co.fullness.bdd.domains.models.products.*;
import jp.co.fullness.bdd.exceptions.InternalException;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * 商品ティティをアクセスするリポジトリインターフェイスの実装
 * @author Fullness,Inc.
 * @since 2025-02-23
 * @version 1.0.1
 */
@Repository
public class ProductRepositoryMyBatis implements ProductRepository {
    // productテーブルアクセスMapperインターフェイス
    private final ProductMapper productMapper;
    // エンティティとProductTableModel変換アダプタ
    private final ProductAdapter<ProductTableModel> productAdapter;

    /**
     * コンストラクタ
     * @param productMapper productテーブルアクセスMapperインターフェイス
     * @param productAdapter エンティティとProductTableModel変換アダプタ
     */
    public ProductRepositoryMyBatis(
            ProductMapper productMapper,
            ProductAdapter<ProductTableModel> productAdapter) {
        this.productMapper = productMapper;
        this.productAdapter = productAdapter;
    }

    /**
     * 指定された商品Idの商品を取得する
     * @param productId 取得対象を表す商品Id
     * @return 取得した商品
     */
    @Override
    public Product findById(ProductId productId) {
        try{
            var productModel = productMapper.findById(productId.getValue());
            if (productModel == null){
                return null;
            }
            return productAdapter.restore(productModel);
        }catch(Exception e){
            throw new InternalException(e.getMessage(),e);
        }
    }

    /**
     * キーワードに部分一致する商品を取得する
     * @param partialName 商品キーワード
     * @return 取得結果
     */
    @Override
    public List<Product> findByNameContains(ProductName partialName) {
        try{
            var productModels = productMapper.findByNameContains(partialName.getValue());
            return productAdapter.restoreList(productModels);
        }catch(Exception e){
            throw new InternalException(e.getMessage(),e);
        }
    }

    /**
     * 商品の存在有無確認結果を返す
     * @param productName 商品名
     * @return true:存在する false:存在しない
     */
    @Override
    public Boolean exists(ProductName productName) {
        try{
            return productMapper.exists(productName.getValue());
        }catch(Exception e){
            throw new InternalException(e.getMessage(),e);
        }
    }

    /**
     * 商品を永続化する
     * @param product 永続化する商品
     */
    @Override
    public void create(Product product) {
        try{
            // 商品を永続化する
            productMapper.create(productAdapter.convert(product));
        }catch(Exception e){
            throw new InternalException(e.getMessage(),e);
        }
    }

    /**
     * 商品を変更する
     * @param product 変更商品
     * @return true:変更成功 false:変更失敗
     */
    @Override
    public Boolean updateById(Product product) {
        try{
            return productMapper.updateById(productAdapter.convert(product));
        }catch(Exception e){
            throw new InternalException(e.getMessage(),e);
        }
    }

    /**
     * 指定された商品Idの商品を削除する
     * @param productId 削除対象の商品Id
     * @return true:変更成功 false:変更失敗
     */
    @Override
    public Boolean deleteById(ProductId productId) {
        try{
            return productMapper.deleteById(productId.getValue());
        }catch(Exception e){
            throw new InternalException(e.getMessage(),e);
        }
    }
}
