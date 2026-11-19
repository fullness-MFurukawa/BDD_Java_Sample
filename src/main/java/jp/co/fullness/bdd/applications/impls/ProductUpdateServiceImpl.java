package jp.co.fullness.bdd.applications.impls;

import jp.co.fullness.bdd.applications.services.ProductUpdateService;
import jp.co.fullness.bdd.domains.models.products.Product;
import jp.co.fullness.bdd.domains.models.products.ProductId;
import jp.co.fullness.bdd.domains.models.products.ProductRepository;
import jp.co.fullness.bdd.exceptions.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;

/**
 * 商品変更サービスインターフェイスの実装
 * @author Fullness,Inc.
 * @since 2025-02-23
 * @version 1.0.1
 */
@Transactional(readOnly = true)
@Service
public class ProductUpdateServiceImpl implements ProductUpdateService {
    private final ProductRepository productRepository;

    /**
     * コンストラクタ
     * @param productRepository 商品アクセスリポジトリ
     */
    public ProductUpdateServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * 指定された商品Idの商品を取得する
     *
     * @param productId 商品Id
     * @return 取得した商品
     */
    @Override
    public Product getProduct(ProductId productId) {
        var result = productRepository.findById(productId);
        if (result == null){
            throw new NotFoundException(
                    MessageFormat.format(
                            "商品Id:{0}に一致する商品は見つかりませんでした。",
                            productId.getValue())
            );
        }
        return result;
    }

    /**
     * 指定された商品を変更する
     *
     * @param product 変更する商品
     */
    @Transactional
    @Override
    public void update(Product product) {
        var result = productRepository.updateById(product);
        if (! result){ // 変更対象の商品が存在しない
            throw new NotFoundException(
                    MessageFormat.format(
                            "商品Id:{0}の商品が見つからないため、変更できませんでした。",
                            product.getProductId().getValue())
            );
        }
    }
}
