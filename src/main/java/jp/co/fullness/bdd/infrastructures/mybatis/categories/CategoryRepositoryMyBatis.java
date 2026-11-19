package jp.co.fullness.bdd.infrastructures.mybatis.categories;

import jp.co.fullness.bdd.domains.models.categories.Category;
import jp.co.fullness.bdd.domains.models.categories.CategoryAdapter;
import jp.co.fullness.bdd.domains.models.categories.CategoryId;
import jp.co.fullness.bdd.domains.models.categories.CategoryRepository;
import jp.co.fullness.bdd.exceptions.InternalException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品カテゴリエンティティをアクセスするリポジトリインターフェイスの実装
 * @author Fullness,Inc.
 * @since 2025-02-23
 * @version 1.0.1
 */
@Repository
public class CategoryRepositoryMyBatis implements CategoryRepository {
    // categoryテーブルアクセスMapperインターフェイス
    private final CategoryMapper categoryMapper;
    // エンティティとCategoryTableModel変換アダプタ
    private final CategoryAdapter<CategoryTableModel> categoryAdapter;

    /**
     * コンストラクタ
     * @param categoryMapper categoryテーブルアクセスMapperインターフェイス
     * @param categoryAdapter エンティティとCategoryTableModel変換アダプタ
     */
    public CategoryRepositoryMyBatis(
            CategoryMapper categoryMapper,
            CategoryAdapter<CategoryTableModel> categoryAdapter) {
        this.categoryMapper = categoryMapper;
        this.categoryAdapter = categoryAdapter;
    }

    /**
     * すべての商品カテゴリを取得する
     * @return 取得した商品カテゴリのリスト
     */
    @Override
    public List<Category> findAll() {
        try{
            var categoryModels = categoryMapper.findAll();
            return categoryAdapter.restoreList(categoryModels);
        }catch(Exception e){
            throw  new InternalException(e.getMessage(),e);
        }
    }

    /**
     * 指定された商品カテゴリIdの商品カテゴリを取得する
     * @param categoryId 取得対象を表すカテゴリId
     * @return 取得した商品カテゴリ
     */
    @Override
    public Category findById(CategoryId categoryId) {
        try{
            var categoryModel = categoryMapper.findById(categoryId.getValue());
            if (categoryModel == null){
                return null;
            }
            return categoryAdapter.restore(categoryModel);
        }catch(Exception e){
            throw  new InternalException(e.getMessage(),e);
        }
    }
}
