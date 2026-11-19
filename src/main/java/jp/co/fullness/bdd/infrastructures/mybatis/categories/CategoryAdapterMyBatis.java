package jp.co.fullness.bdd.infrastructures.mybatis.categories;

import jp.co.fullness.bdd.domains.models.categories.Category;
import jp.co.fullness.bdd.domains.models.categories.CategoryAdapter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品カテゴリエンティティをテーブルモデルに変換および復元する
 * @author Fullness,Inc.
 * @version 1.0
 * @since 2024/10/25
 */
@Component
public class CategoryAdapterMyBatis implements CategoryAdapter<CategoryTableModel> {
    /**
     * 商品カテゴリエンティティを他のモデルに変換した結果を返す
     *
     * @param source 商品カテゴリエンティティ
     * @return 変換結果
     */
    @Override
    public CategoryTableModel convert(Category source) {
        var model =  new CategoryTableModel();
        model.setObjId(source.getCategoryId().getValue());
        model.setName(source.getCategoryName().getValue());
        return model;
    }
    /**
     * 商品カテゴリエンティティリストを他のモデルのリストに変換した結果を返す
     *
     * @param sources 商品カテゴリのリスト
     * @return 変換結果
     */
    @Override
    public List<CategoryTableModel> convertList(List<Category> sources) {
        var models = new ArrayList<CategoryTableModel>();
        sources.forEach(c -> models.add(this.convert(c)));
        return models;
    }
    /**
     * 他のモデルから商品カテゴリエンティティを復元する
     *
     * @param source 他のモデル
     * @return 復元結果
     */
    @Override
    public Category restore(CategoryTableModel source) {
        return new Category(source.getObjId(),source.getName());
    }
    /**
     * 他のモデルのリストから商品カテゴリエンティティのリストを復元する
     *
     * @param sources 他のモデルのリスト
     * @return 復元結果
     */
    @Override
    public List<Category> restoreList(List<CategoryTableModel> sources) {
        var categories = new ArrayList<Category>();
        sources.forEach(m -> categories.add(this.restore(m)));
        return categories;
    }
}
