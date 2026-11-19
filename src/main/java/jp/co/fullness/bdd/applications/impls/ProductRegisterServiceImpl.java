package jp.co.fullness.bdd.applications.impls;

import jp.co.fullness.bdd.applications.services.ProductRegisterService;
import jp.co.fullness.bdd.domains.models.categories.Category;
import jp.co.fullness.bdd.domains.models.categories.CategoryId;
import jp.co.fullness.bdd.domains.models.categories.CategoryRepository;
import jp.co.fullness.bdd.domains.models.products.Product;
import jp.co.fullness.bdd.domains.models.products.ProductName;
import jp.co.fullness.bdd.domains.models.products.ProductRepository;
import jp.co.fullness.bdd.exceptions.ExistsException;
import jp.co.fullness.bdd.exceptions.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;

/**
 * 商品登録サービスインターフェイスの実装
 * @author Fullness,Inc.
 * @since 2025-02-23
 * @version 1.0.1
 */
@Transactional(readOnly = true)
@Service
public class ProductRegisterServiceImpl implements ProductRegisterService {
    // 商品カテゴリアクセスリポジトリ
    private final CategoryRepository categoryRepository;
    // 商品アクセスリポジトリ
    private final ProductRepository productRepository;

    /**
     * コンストラクタ
     * @param categoryRepository 商品カテゴリアクセスリポジトリ
     * @param productRepository 商品アクセスリポジトリ
     */
    public ProductRegisterServiceImpl(
            CategoryRepository categoryRepository,
            ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    /**
     * すべての商品カテゴリを取得する
     *
     * @return 商品カテゴリリスト
     */
    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    /**
     * 指定された商品カテゴリIdの商品カテゴリを取得する
     *
     * @param categoryId 商品カテゴリId
     * @return 商品カテゴリ
     */
    @Override
    public Category getCategory(CategoryId categoryId) {
        var result = categoryRepository.findById(categoryId);
        if (result == null){
            throw new NotFoundException(
                    MessageFormat.format(
                       "商品カテゴリId:{0}に一致する商品カテゴリが見つかりませんでした。",
                       categoryId.getValue()));
        }
        return result;
    }

    /**
     * 指定された商品の有無を調べる
     *
     * @param productName 有無を調べる商品名
     */
    @Override
    public void exists(ProductName productName) {
        var result = productRepository.exists(productName);
        if (result){ // 既に同一商品が存在する
            throw new ExistsException(
                    MessageFormat.format(
                            "商品名:{0}は既に存在します。",
                            productName.getValue()));
        }
    }

    /**
     * 指定された商品を永続化する
     *
     * @param product 永続化する商品
     */
    @Transactional
    @Override
    public void register(Product product) {
        productRepository.create(product);
    }
}
