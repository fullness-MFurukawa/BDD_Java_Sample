package jp.co.fullness.bdd.applications.impls;

import jp.co.fullness.bdd.applications.services.ProductKeywordSearchService;
import jp.co.fullness.bdd.domains.models.products.Product;
import jp.co.fullness.bdd.domains.models.products.ProductName;
import jp.co.fullness.bdd.domains.models.products.ProductRepository;
import jp.co.fullness.bdd.exceptions.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;

/**
 * 商品キーワード検索サービスインターフェイスの実装
 * @author Fullness,Inc.
 * @since 2025-02-23
 * @version 1.0.1
 */
@Transactional(readOnly = true)
@Service
public class ProductKeywordSearchServiceImpl implements ProductKeywordSearchService {
    private final ProductRepository productRepository;
    /**
     * コンストラクタ
     * @param productRepository 商品アクセスリポジトリ
     */
    public ProductKeywordSearchServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * 商品キーワード検索
     *
     * @param keyword 商品キーワード
     * @return キーワード検索結果
     */
    @Override
    public List<Product> execute(ProductName keyword) {
        var results = productRepository.findByNameContains(keyword);
        if (results == null){
            throw new NotFoundException(
                    MessageFormat.format(
                            "キーワード:{0}に該当する商品は見つかりませんでした。",
                            keyword.getValue()));
        }
        return results;
    }
}
